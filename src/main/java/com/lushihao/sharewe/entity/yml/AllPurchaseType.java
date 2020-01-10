package com.lushihao.sharewe.entity.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "purchasetype")  // 配置文件中的前缀
public class AllPurchaseType {

    private List<PurchaseType> typeList = new ArrayList<>();

    public List<PurchaseType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<PurchaseType> typeList) {
        this.typeList = typeList;
    }

    public List<PurchaseType> getItem(PurchaseType purchaseType) {
        List<PurchaseType> list = new ArrayList<>();
        list.addAll(typeList);
        if (purchaseType.getId() != 0) {
            list = typeList.stream().filter(s -> s.getId() == purchaseType.getId()).collect(Collectors.toList());
        }
        if (purchaseType.getName() != null) {
            list = typeList.stream().filter(s -> s.getName().equals(purchaseType.getName())).collect(Collectors.toList());
        }
        if (purchaseType.getCode() != null) {
            list = typeList.stream().filter(s -> s.getCode().equals(purchaseType.getCode())).collect(Collectors.toList());
        }
        return list;
    }

}
