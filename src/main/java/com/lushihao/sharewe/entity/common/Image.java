package com.lushihao.sharewe.entity.common;

import java.util.Date;

public class Image {

    /**
     * 用户类型_标识（用户、商家）
     */
    private String userInfoId;
    /**
     * 类型_标识（例如：confessionWall_1）
     */
    private String typeId;
    /**
     * 名称
     */
    private String name;
    /**
     * 地址
     */
    private String src;
    /**
     * 发送时间
     */
    private Date saveTime;

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

}
