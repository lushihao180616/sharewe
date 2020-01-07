package com.lushihao.sharewe.init;

import com.lushihao.sharewe.config.JobClassesConfig;
import com.lushihao.sharewe.config.QuartzConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitProject implements ApplicationRunner {
    @Autowired
    private QuartzConfig quartzConfig;
    @Autowired
    private JobClassesConfig jobClassesConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, String> map = jobClassesConfig.getJobMap();
        quartzConfig.startJob(map);
    }
}