package com.zbw.springboot.service;

import com.zbw.springboot.pojo.ExportUser;
import com.zbw.springboot.pojo.InputCondition;

import java.util.List;

/**
 * Created by 郑博文 on 2019/10/11.
 */
public interface TimerStatisticService {
    /**
     * 根据条件查询用户
     * @param inputCondition
     * @return
     */
    List<ExportUser> getExportUser(InputCondition inputCondition);

    /**
     * 定时任务
     */
    void timerStatistics();
}
