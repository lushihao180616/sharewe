package com.lushihao.sharewe.entity.userinfo;

public class UserProfessionTypeRecord {

    /**
     * 用户标识
     */
    private String openId;
    /**
     * 类型
     */
    private String typeCode;
    /**
     * 是否开启（0：禁用，1：启用）
     */
    private boolean ifOpen;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public boolean isIfOpen() {
        return ifOpen;
    }

    public void setIfOpen(boolean ifOpen) {
        this.ifOpen = ifOpen;
    }

}
