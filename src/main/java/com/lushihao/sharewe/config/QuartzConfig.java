package com.lushihao.sharewe.config;

import com.lushihao.sharewe.quartzjobs.Job0;
import com.lushihao.sharewe.util.LSHBeanUtils;
import com.lushihao.sharewe.util.LSHPropertyUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import javax.annotation.Resource;
import java.util.*;

@Configuration
public class QuartzConfig extends WebApplicationObjectSupport {

    @Autowired
    private LSHPropertyUtils lshPropertyUtils;
    /**
     * 任务调度
     */
    @Resource
    private Scheduler scheduler;
    /**
     * 键：job类名，值：group组名
     */
    public Map<String, String> job_group = new HashMap<>();

    /**
     * 开始执行定时任务
     *
     * @throws SchedulerException
     */
    public void startJob(Map<String, String> map) throws SchedulerException, ClassNotFoundException {
        String jobClassPath = LSHPropertyUtils.getPropertiesValue("jobClassPath");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String job = entry.getKey();
            String group = entry.getValue().split(",")[0];
            String cron = entry.getValue().split(",")[1];
            job_group.put(job, group);
            Class clazz = Class.forName(jobClassPath + job);
            startJobTask(scheduler, clazz, job, group, cron);
        }
        scheduler.start();
    }

    /**
     * 启动定时任务
     *
     * @param scheduler
     * @throws SchedulerException
     */
    private void startJobTask(Scheduler scheduler, Class clazz, String job, String group, String cron) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job, group).build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(job, group)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 获取Job信息
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public String getjobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }

    /**
     * 修改任务的执行时间
     *
     * @param name
     * @param group
     * @param cron  cron表达式
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(String name, String group, String cron) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(cron)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 暂停所有任务
     *
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 暂停某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复所有任务
     *
     * @throws SchedulerException
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.deleteJob(jobKey);
    }

}
