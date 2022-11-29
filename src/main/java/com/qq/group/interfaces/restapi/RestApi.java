package com.qq.group.interfaces.restapi;

import com.qq.group.application.dto.request.GroupMsgRequestDto;
import com.qq.group.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HuoWB
 * @since 2022/11/11 16:01
 */
@RestController
public class RestApi {

    @Autowired
    ApplicationService applicationService;


    /**
     * 服务消息上报监听
     *
     * @param dto
     * @return void
     * @author HuoWBe
     * @since 2022/11/14 16:10
     */
    @PostMapping
    public void qqGroupMsgUpMonitor(@RequestBody GroupMsgRequestDto dto) {
        applicationService.messageMonitor(dto);
    }

    /**
     * @param num
     * @param name
     * @param orName
     * @param acronyms
     * @param fileType
     * @return void
     * @author HuoWB
     * @since 2022/11/17 8:29
     */
    @PostMapping("/create")
    public String createPets(int num, String name, String orName, String acronyms, String fileType) {
        applicationService.createPets(num, name, orName, acronyms, fileType);
        return "成功";
    }


}
