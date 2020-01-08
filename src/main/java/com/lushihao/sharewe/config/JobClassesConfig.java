package com.lushihao.sharewe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component      //不加这个注解的话, 使用@Autowired 就不能注入进去了
@ConfigurationProperties(prefix = "jobclass")  // 配置文件中的前缀
public class JobClassesConfig {

    private List<Map<String, String>> jobList = new ArrayList<>();

    public List<Map<String, String>> getJobList() {
        return jobList;
    }

    public void setJobList(List<Map<String, String>> jobList) {
        this.jobList = jobList;
    }
}