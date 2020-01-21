package com.lushihao.sharewe.entity.common;

import java.util.Date;

public class Image {

    /**
     * 标识（可能是用户标识也可能是商家标识，即userOpenId或merchantCode）
     */
    private String id;
    /**
     * 用户类型（用户、商家）
     */
    private int userInfoTypeId;
    /**
     * 是属于哪个类型的哪条数据的标识（例如：confessionWall_1）
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserInfoTypeId() {
        return userInfoTypeId;
    }

    public void setUserInfoTypeId(int userInfoTypeId) {
        this.userInfoTypeId = userInfoTypeId;
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
