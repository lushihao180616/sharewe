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

    /**
     * 获取一条数据
     *
     * @param expressType
     * @return
     */
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

    /**
     * 通过id获取类型
     *
     * @param codes
     * @return
     */
    public List<ExpressType> getItemByCodes(String codes) {
        List<ExpressType> list = new ArrayList<>();
        list.addAll(typeList);
        return list.stream().filter(s -> codes.contains(String.valueOf(s.getCode()))).collect(Collectors.toList());
    }

}
