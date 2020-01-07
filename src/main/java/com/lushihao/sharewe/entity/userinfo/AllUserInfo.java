package com.lushihao.sharewe.entity.userinfo;

import java.util.List;
import java.util.Map;

public class AllUserInfo {
	private UserInfo userinfo;
	private List<Province> provinceList;
	private List<School> schoolList;
	private List<Building> buildingList;
	private List<Building> dormitoryList;
	private List<Map<String,Object>> addressList;

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

	public List<Map<String,Object>> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Map<String,Object>> addressList) {
		this.addressList = addressList;
	}
}
