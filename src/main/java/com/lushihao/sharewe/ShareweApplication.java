package com.lushihao.sharewe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lushihao.model.dao")
public class ShareweApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareweApplication.class, args);
    }

}
