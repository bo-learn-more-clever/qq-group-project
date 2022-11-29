package com.qq.group.infrastructure.task;

import com.qq.group.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author HuoWB
 * @since 2022/11/16 15:16
 */
@EnableScheduling
@EnableAsync
@Component
public class Task {

    @Autowired
    ApplicationService applicationService;

    /**
     * 18点整
     */
    @Scheduled(cron = "0 59 17 ? * 5")
    public void perMinute() {
        String text = "各位亲们~双倍经验就要开始了~今晚18:00~21:00为双倍经验时间！";
        String atAll = "[CQ:at,qq=all]";
        applicationService.sendMsg("704916345", 1, text, null);
        applicationService.sendMsg("704916345", 1, text, null);
        applicationService.sendMsg("704916345", 1, text + "(重要的事情说三遍)!!!", null);
    }

    /**
     * 18点整
     */
    @Scheduled(cron = "0 59 17 ? * 6")
    public void perMinute1() {
        String text = "各位亲们~双倍经验就要开始了~今晚18:00~21:00为双倍经验时间！";
        String atAll = "[CQ:at,qq=all]";
        applicationService.sendMsg("704916345", 1, text, null);
        applicationService.sendMsg("704916345", 1, text, null);
        applicationService.sendMsg("704916345", 1, text + "(重要的事情说三遍)!!!", null);
    }

    /**
     * 18点整
     */
    @Scheduled(cron = "0 59 17 ? * 7")
    public void perMinute2() {
        String text = "各位亲们~双倍经验就要开始了~今晚18:00~21:00为双倍经验时间！";
        String atAll = "[CQ:at,qq=all]";
        applicationService.sendMsg("704916345", 1, text, null);
        applicationService.sendMsg("704916345", 1, text, null);
        applicationService.sendMsg("704916345", 1, text + "(重要的事情说三遍)!!!", null);
    }


}
