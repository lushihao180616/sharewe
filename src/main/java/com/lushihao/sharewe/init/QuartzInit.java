package com.lushihao.sharewe.init;

import com.lushihao.sharewe.config.quartz.JobClassesConfig;
import com.lushihao.sharewe.config.quartz.QuartzConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 初始化项目
 */
@Component
public class QuartzInit implements ApplicationRunner {

    @Autowired
    private QuartzConfig quartzConfig;
    @Autowired
    private JobClassesConfig jobClassesConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Map<String, String>> list = jobClassesConfig.getJobList();
        quartzConfig.startJob(list);
    }

}