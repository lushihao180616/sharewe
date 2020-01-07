package com.lushihao.sharewe.entity.userinfo;

public class Building {
	/**
	 * ������Ψһ��ʶ
	 */
	private int id;
	/**
	 * ����������
	 */
	private String name;
	/**
	 * ���������� 0������¥��1����ѧ¥��2��ʵ��¥��3������¥
	 */
	private int type;
	/**
	 * ѧУ����
	 */
	private int school_id;
	/**
	 * ѧУ����
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

	public Building(int id, String name, int type, int school_id, String school_name) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.school_id = school_id;
		this.school_name = school_name;
	}

	public Building() {
		super();
	}

	@Override
	public String toString() {
		return "Building [id=" + id + ", name=" + name + ", type=" + type + ", school_id=" + school_id
				+ ", school_name=" + school_name + "]";
	}

}
