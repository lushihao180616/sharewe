package com.lushihao.sharewe.config.yml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class YmlDataInit {

    /**
     * 配置yml文件
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(
                new ClassPathResource("job-classes.yml"),
                new ClassPathResource("project-basic-info.yml"),
                new ClassPathResource("purchase-type.yml"),
                new ClassPathResource("merchant-type.yml")
        );
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

}
