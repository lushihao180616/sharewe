package com.lushihao.sharewe.entity.userinfo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "userinfotype")  // 配置文件中的前缀
public class AllUserInfoType {

    private List<UserInfoType> typeList = new ArrayList<>();

    public List<UserInfoType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<UserInfoType> typeList) {
        this.typeList = typeList;
    }

    public List<UserInfoType> getItem(String code) {
        List<UserInfoType> list = new ArrayList<>();
        list.addAll(typeList);
        if (code != null) {
            list = typeList.stream().filter(s -> s.getCode().equals(code)).collect(Collectors.toList());
        }
        return list;
    }

}
