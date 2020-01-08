package com.lushihao.sharewe.entity.userinfo;

import java.util.List;
import java.util.Map;

public class AllUserInfo {
    /**
     * 用户
     */
    private UserInfo userinfo;
    /**
     * 省份
     */
    private List<Province> provinceList;
    /**
     * 学校
     */
    private List<School> schoolList;
    /**
     * 建筑物
     */
    private List<Building> buildingList;
    /**
     * 宿舍
     */
    private List<Building> dormitoryList;
    /**
     * 地址
     */
    private List<Map<String, Object>> addressList;

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public List<Province> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

    public List<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<Building> getDormitoryList() {
        return dormitoryList;
    }

    public void setDormitoryList(List<Building> dormitoryList) {
        this.dormitoryList = dormitoryList;
    }

    public List<Map<String, Object>> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Map<String, Object>> addressList) {
        this.addressList = addressList;
    }
}
