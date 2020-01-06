package com.lushihao.sharewe.entity;

public class Address {
    private int id;
    private int building_id;
    private String roomcode;
    private String name;
    private String phoneNumber;
    private String openId;
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

    public Address(int id, int building_id, String roomcode, String name, String phoneNumber, String openId, int usedCount) {
        super();
        this.id = id;
        this.building_id = building_id;
        this.roomcode = roomcode;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.openId = openId;
        this.usedCount = usedCount;
    }

    public Address() {
        super();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", building_id=" + building_id +
                ", roomcode='" + roomcode + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", openId='" + openId + '\'' +
                ", usedCount=" + usedCount +
                '}';
    }
}
