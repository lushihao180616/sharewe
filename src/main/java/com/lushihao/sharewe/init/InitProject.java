package com.lushihao.sharewe.init;

import com.lushihao.sharewe.config.QuartzConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitProject implements ApplicationRunner {
    @Autowired
    private QuartzConfig quartzConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        quartzConfig.startJob();
    }
}