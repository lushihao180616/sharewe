package com.lushihao.sharewe.entity.userinfo;

public class UserInfoType {

    /**
     * 名称
     */
    private String name;
    /**
     * 类型代号
     */
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserInfoType() {
    }

    public UserInfoType(String name, String code) {
        this.name = name;
        this.code = code;
    }

}
