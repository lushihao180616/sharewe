package com.lushihao.sharewe.entity.merchant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "merchanttype")  // 配置文件中的前缀
public class AllMerchantType {

    private List<MerchantType> typeList = new ArrayList<>();

    public List<MerchantType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<MerchantType> typeList) {
        this.typeList = typeList;
    }

    public List<MerchantType> getItem(MerchantType merchantType) {
        List<MerchantType> list = new ArrayList<>();
        list.addAll(typeList);
        if (merchantType.getId() != 0) {
            list = typeList.stream().filter(s -> s.getId() == merchantType.getId()).collect(Collectors.toList());
        }
        if (merchantType.getName() != null) {
            list = typeList.stream().filter(s -> s.getName().equals(merchantType.getName())).collect(Collectors.toList());
        }
        if (merchantType.getCode() != null) {
            list = typeList.stream().filter(s -> s.getCode().equals(merchantType.getCode())).collect(Collectors.toList());
        }
        return list;
    }

    /**
     * 通过id获取商家类型
     * @param ids
     * @return
     */
    public List<MerchantType> getItemByIds(String ids) {
        List<MerchantType> list = new ArrayList<>();
        list.addAll(typeList);
        return list.stream().filter(s -> ids.contains(String.valueOf(s.getId()))).collect(Collectors.toList());
    }

}
