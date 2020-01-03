package com.lushihao.sharewe.entity;

public class UserInfo {
    /**
     * 用户唯一标识
     */
    private String openId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户性别1：男|0：女
     */
    private String gender;
    /**
     * 省份代号
     */
    private int province_id;
    /**
     * 学校代号
     */
    private int school_id;
    /**
     * 宿舍代号
     */
    private int building_id;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 捎点
     */
    private Double point;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public UserInfo(String openId, String nickName, String gender, int province_id, int school_id, int building_id,
                    String phoneNumber, String avatarUrl, Double point) {
        super();
        this.openId = openId;
        this.nickName = nickName;
        this.gender = gender;
        this.province_id = province_id;
        this.school_id = school_id;
        this.building_id = building_id;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
        this.point = point;
    }

    public UserInfo() {
        super();
    }

    @Override
    public String toString() {
        return "UserInfo [openId=" + openId + ", nickName=" + nickName + ", gender=" + gender + ", province_id="
                + province_id + ", school_id=" + school_id + ", building_id=" + building_id + ", phoneNumber="
                + phoneNumber + ", avatarUrl=" + avatarUrl + ", point=" + point + "]";
    }


}
