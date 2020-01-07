package com.lushihao.sharewe.entity.userinfo;

/**
 * @author Administrator �û�ѧУbean
 */
public class School {
	/**
	 * ѧУΨһ��ʶ
	 */
	private int id;
	/**
	 * ѧУ����
	 */
	private String name;
	/**
	 * ѧУʡ�ݴ���
	 */
	private int province_id;
	/**
	 * ѧУʡ������
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

	public School(int id, String name, int province_id, String province_name) {
		super();
		this.id = id;
		this.name = name;
		this.province_id = province_id;
		this.province_name = province_name;
	}

	public School() {
		super();
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", province_id=" + province_id + ", province_name="
				+ province_name + "]";
	}

}
