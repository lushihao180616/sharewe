package com.lushihao.sharewe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component      //不加这个注解的话, 使用@Autowired 就不能注入进去了
@ConfigurationProperties(prefix = "jobclass")  // 配置文件中的前缀
public class JobClassesConfig {
    /**
     * 从配置文件中读取的jobMap开头的数据
     * 注意：名称必须与配置文件中保持一致
     */
    private Map<String, String> jobMap = new HashMap<>();

    private List<Map<String, String>> jobList = new ArrayList<>();

    public Map<String, String> getJobMap() {
        return jobMap;
    }

    public void setJobMap(Map<String, String> jobMap) {
        this.jobMap = jobMap;
    }

    public List<Map<String, String>> getJobList() {
        return jobList;
    }

    public void setJobList(List<Map<String, String>> jobList) {
        this.jobList = jobList;
    }
}
