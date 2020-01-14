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

    public List<UserInfoType> getItem(UserInfoType userInfoType) {
        List<UserInfoType> list = new ArrayList<>();
        list.addAll(typeList);
        if (userInfoType.getId() != 0) {
            list = typeList.stream().filter(s -> s.getId() == userInfoType.getId()).collect(Collectors.toList());
        }
        if (userInfoType.getName() != null) {
            list = typeList.stream().filter(s -> s.getName().equals(userInfoType.getName())).collect(Collectors.toList());
        }
        if (userInfoType.getCode() != null) {
            list = typeList.stream().filter(s -> s.getCode().equals(userInfoType.getCode())).collect(Collectors.toList());
        }
        return list;
    }

}
