package com.zbw.springboot.controller;

import com.zbw.springboot.service.TimerStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 郑博文 on 2019/10/11.
 */
@Component
public class ScheduledTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private TimerStatisticService timerStatisticService;


      //@Scheduled(cron = "0 0/1 * * * ?")
      @Scheduled(cron = "0 1 0 * * ?")
      public void reportCurrentTime() {
        System.out.println("开始时间：" + dateFormat.format(new Date()));
        timerStatisticService.timerStatistics();
        System.out.println("结束时间：" + dateFormat.format(new Date()));
    }

}
