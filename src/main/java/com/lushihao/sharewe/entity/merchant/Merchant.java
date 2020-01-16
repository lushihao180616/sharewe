package com.lushihao.sharewe.entity.merchant;

import java.util.List;

public class Merchant {

    /**
     * 代号
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 商家头像
     */
    private String iconSrc;
    /**
     * 地址
     */
    private String address;
    /**
     * 类型
     */
    private String types;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

}
