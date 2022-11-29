package com.qq.group.application.service;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import com.qq.group.application.dto.request.GroupMsgRequestDto;
import com.qq.group.application.dto.request.SendMsgRequestDto;
import com.qq.group.infrastructure.po.AutoMsgPo;
import com.qq.group.infrastructure.po.OtherPo;
import com.qq.group.infrastructure.po.PetsPo;
import com.qq.group.infrastructure.repository.AutoRepository;
import com.qq.group.infrastructure.repository.OtherRepository;
import com.qq.group.infrastructure.repository.PetsRepository;
import com.qq.group.infrastructure.utils.SendHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author HuoWB
 * @since 2022/11/14 16:13
 */
@Service
public class ApplicationService {

    @Autowired
    PetsRepository petsRepository;
    @Autowired
    OtherRepository otherRepository;
    @Autowired
    AutoRepository autoRepository;


    /**
     * 群消息监听
     *
     * @param dto
     * @return void
     * @author HuoWB
     * @since 2022/11/14 16:16
     */
    public void messageMonitor(GroupMsgRequestDto dto) {
        // 如果消息不为空
        if (dto.getMessage() != null) {
            System.out.println("-----" + dto);

            // 如果消息体包含资料卡或zlk首字母缩写
            if ((dto.getMessage().contains("zlk") || dto.getMessage().contains("资料卡"))) {
                // 发送资料卡
                getZlk(dto.getMessage(), dto.getGroup_id());
            } else if (dto.getMessage().contains("[CQ:at,qq=3501481533]")) {
                // at助手
                String text = "[CQ:at,qq=" + dto.getUser_id() + "]";
                // 如果是设置回复消息
                if (dto.getMessage().contains("设置：") || dto.getMessage().contains("设置:")) {
                    String returnMsg = dto.getMessage().replace("设置：", "").replace("设置:", "");
                    if (!returnMsg.contains("。")) {
                        text = text + "格式不对哦，必须包含句号的！";
                        sendMsg(dto.getGroup_id(), 1, text, null);
                        return;
                    }
                    String[] split = returnMsg.split("。");
                    if (split.length > 2) {
                        text = text + "格式不对哦，只能包含一个句号的！";
                        sendMsg(dto.getGroup_id(), 1, text, null);
                        return;
                    }
                    // 删除at的数据
                    String replace = split[0].replace("[CQ:at,qq=3501481533]", "");
                    AutoMsgPo byAuto = autoRepository.getByAuto(replace);
                    if (byAuto != null) {
                        text = text + "姐早就已经学会这个了！不需要学习两次！";
                        sendMsg(dto.getGroup_id(), 1, text, null);
                        return;
                    }
                    AutoMsgPo autoMsgPo = new AutoMsgPo();
                    autoMsgPo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                    autoMsgPo.setAuto(replace);
                    autoMsgPo.setReturnMsg(split[1]);
                    autoRepository.create(autoMsgPo);
                    text = text + "太简单了！姐已经学会了！";
                    sendMsg(dto.getGroup_id(), 1, text, null);
                } else if ("[CQ:at,qq=3501481533]".equals(dto.getMessage())) {
                    // 如果只是AT
                    text = text + "叫姐干嘛！姐很忙的！";
                    sendMsg(dto.getGroup_id(), 1, text, null);
                } else {
                    // 如果at了还包含信息了
                    String selectMsg = dto.getMessage().replace("[CQ:at,qq=3501481533]", "");
                    AutoMsgPo byAuto = autoRepository.getByAuto(selectMsg);
                    if (byAuto != null) {
                        text = text + byAuto.getReturnMsg();
                    } else {
                        text = text + "叫姐干嘛";
                    }
                    sendMsg(dto.getGroup_id(), 1, text, null);
                }
            } else if (msgConstantKey(dto.getMessage())) {
                // 其他配置
                OtherPo otherPoByName = otherRepository.getOtherPoByName(dto.getMessage());
                if (otherPoByName != null) {
                    // 图片
                    if (otherPoByName.getUrl().contains("jpg") || otherPoByName.getUrl().contains("png") && !otherPoByName.getUrl().contains("CQ")) {
                        sendMsg(dto.getGroup_id(), 2, null, otherPoByName.getUrl());
                    } else {
                        // 文字
                        sendMsg(dto.getGroup_id(), 1, otherPoByName.getUrl(), null);
                    }
                }
            }
        }
    }

    /**
     * 获取资料卡
     *
     * @param msg
     * @param groupId
     * @return void
     * @author HuoWB
     * @since 2022/11/17 16:16
     */
    private void getZlk(String msg, String groupId) {
        // 分割取消补位
        String substring = msg.substring(0, msg.length() - 3);
        PetsPo petsByAcronyms = petsRepository.getPetsByAcronyms(substring);
        // 没有首字母的
        if (petsByAcronyms == null) {
            petsByAcronyms = petsRepository.getPetsPoByOrName(substring);
            // 没有别名的
            if (petsByAcronyms == null) {
                petsByAcronyms = petsRepository.getPetsPoByName(substring);
                // 连名字都不是的
                if (petsByAcronyms == null) {
//                    sendMsg(groupId, 1, "暂未录入资料卡", null);
                    return;
                }
            }
        }
        // 发送消息
        sendMsg(groupId, 2, null, petsByAcronyms.getZlkUrl());
        // 查询的资料是牛姐
        if ("2810".equals(petsByAcronyms.getId())) {
            sendMsg(groupId, 1, "这是本小姐哦！", null);
        }
    }

    /**
     * 发送消息
     *
     * @param groupId 群id
     * @param type    类型：1-文字；2-图片
     * @param text    文字的内容
     * @param fileUrl 图片的文件Url
     * @return void
     * @author HuoWB
     * @since 2022/11/14 16:29
     */
    public void sendMsg(String groupId, int type, String text, String fileUrl) {
        String url = "http://127.0.0.1:5700/send_group_msg";
        // 参数
        SendMsgRequestDto requestDto = new SendMsgRequestDto();
        requestDto.setGroup_id(groupId);
        // 消息类型
        if (type == 1) {
            if (!StringUtils.isNullOrEmpty(text)) {
                requestDto.setMessage(text);
            }
        } else if (type == 2) {
            if (!StringUtils.isNullOrEmpty(fileUrl)) {
                requestDto.setMessage("[CQ:image,file=" + fileUrl + "]");
            }
        }
        String param = JSONObject.toJSONString(requestDto);
        try {
            JSONObject jsonObject = SendHttpUtil.sendPostHttp(url, param);
            System.out.println("result=" + jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 其他消息筛选
     *
     * @param key
     * @return java.lang.Boolean
     * @author HuoWB
     * @since 2022/11/17 16:17
     */
    private Boolean msgConstantKey(String key) {
        List<String> list = new ArrayList<>();
        list.add("攻略");
        list.add("加成");
        list.add("属性克制");
        list.add("老婆");
        list.add("更新");
        list.add("天梯禁宠");
        list.add("属性");
        list.add("伤害");

        return list.stream().anyMatch(key::contains);
    }

    /**
     * 创建数据
     *
     * @param num
     * @param name
     * @param orName
     * @param acronyms
     * @param fileType
     * @return void
     * @author HuoWB
     * @since 2022/11/17 8:29
     */
    public void createPets(int num, String name, String orName, String acronyms, String fileType) {
        PetsPo petsRepositoryById = petsRepository.getById(num + "");
        if (petsRepositoryById != null) {
            return;
        }
        PetsPo petsPo = new PetsPo();
        petsPo.setId(num);
        petsPo.setName(name);
        petsPo.setOrName(orName);
        petsPo.setAcronyms(acronyms);
        petsPo.setZlkUrl(num + "." + fileType);
        petsRepository.create(petsPo);
    }


}
