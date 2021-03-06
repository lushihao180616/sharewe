package com.lushihao.sharewe.entity.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "jobclass")  // 配置文件中的前缀
public class JobClasses {

    /**
     * 华尔兹任务集合
     */
    private List<Map<String, String>> jobList;
    /**
     * 华尔兹任务类路径
     */
    private String jobClassPath;

    public List<Map<String, String>> getJobList() {
        return jobList;
    }

    public void setJobList(List<Map<String, String>> jobList) {
        this.jobList = jobList;
    }

    public String getJobClassPath() {
        return jobClassPath;
    }

    public void setJobClassPath(String jobClassPath) {
        this.jobClassPath = jobClassPath;
    }

}
