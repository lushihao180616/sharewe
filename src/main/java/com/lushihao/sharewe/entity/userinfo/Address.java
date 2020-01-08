package com.lushihao.sharewe.entity.userinfo;

public class Address {
    /**
     * 标识
     */
    private int id;
    /**
     * 建筑物
     */
    private int building_id;
    /**
     * 房间
     */
    private String roomcode;
    /**
     * 名称
     */
    private String name;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 是哪个用户的
     */
    private String openId;
    /**
     * 被使用次数
     */
    private int usedCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public String getRoomcode() {
        return roomcode;
    }

    public void setRoomcode(String roomcode) {
        this.roomcode = roomcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }
}
