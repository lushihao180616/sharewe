package com.lushihao.sharewe.entity.express;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "expresstype")  // 配置文件中的前缀
public class AllExpressType {

    private List<ExpressType> typeList = new ArrayList<>();

    public List<ExpressType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<ExpressType> typeList) {
        this.typeList = typeList;
    }

    public List<ExpressType> getItem(ExpressType expressType) {
        List<ExpressType> list = new ArrayList<>();
        list.addAll(typeList);
        if (expressType.getName() != null) {
            list = typeList.stream().filter(s -> s.getName().equals(expressType.getName())).collect(Collectors.toList());
        }
        if (expressType.getCode() != null) {
            list = typeList.stream().filter(s -> s.getCode().equals(expressType.getCode())).collect(Collectors.toList());
        }
        return list;
    }

}
