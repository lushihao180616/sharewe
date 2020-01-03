package com.lushihao.sharewe.entity;

/**
 * @author Administrator �û�ʡ��bean
 */
public class Province {
	/**
	 * ʡ��Ψһ��ʶ
	 */
	private int id;
	/**
	 * ʡ������
	 */
	private String name;

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

	public Province(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Province [id=" + id + ", name=" + name + "]";
	}

	public Province() {
		super();
	}


}
