package com.lushihao.sharewe.entity.userinfo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "userprofessiontype")  // 配置文件中的前缀
public class AllUserProfessionType {

    private List<UserProfessionType> typeList = new ArrayList<>();

    public List<UserProfessionType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<UserProfessionType> typeList) {
        this.typeList = typeList;
    }

    public List<UserProfessionType> getItem(UserProfessionType userProfessionType) {
        List<UserProfessionType> list = new ArrayList<>();
        list.addAll(typeList);
        if (userProfessionType.getName() != null) {
            list = typeList.stream().filter(s -> s.getName().equals(userProfessionType.getName())).collect(Collectors.toList());
        }
        if (userProfessionType.getCode() != null) {
            list = typeList.stream().filter(s -> s.getCode().equals(userProfessionType.getCode())).collect(Collectors.toList());
        }
        if (userProfessionType.getDes() != null) {
            list = typeList.stream().filter(s -> s.getCode().equals(userProfessionType.getDes())).collect(Collectors.toList());
        }
        return list;
    }

}
