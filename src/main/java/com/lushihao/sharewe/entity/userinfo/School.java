package com.lushihao.sharewe.entity.userinfo;

/**
 * @author Administrator �û�ѧУbean
 */
public class School {
	/**
	 * 标识
	 */
	private int id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 省份
	 */
	private int province_id;
	/**
	 * 省份名
	 */
	private String province_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProvince_id() {
		return province_id;
	}

	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

}
