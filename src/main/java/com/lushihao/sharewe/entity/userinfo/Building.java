package com.lushihao.sharewe.entity.userinfo;

public class Building {

	/**
	 * 标识
	 */
	private int id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 学校
	 */
	private int school_id;
	/**
	 * 学校名称
	 */
	private String school_name;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

}
