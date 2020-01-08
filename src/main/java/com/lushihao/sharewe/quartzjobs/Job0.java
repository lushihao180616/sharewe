package com.lushihao.sharewe.quartzjobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

public class Job0 extends QuartzJobBean  {
    /**
     * 需要执行的具体任务
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("-----------------Job0");
    }
}