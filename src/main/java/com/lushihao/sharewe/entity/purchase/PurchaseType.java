package com.lushihao.sharewe.entity.purchase;

public class PurchaseType {
	/**
	 * ��������id
	 */
	private int id;
	/**
	 * ������������
	 */
	private String name;
	/**
	 * �������ʹ���
	 */
	private String code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public PurchaseType(int id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public PurchaseType() {
		super();
	}

	@Override
	public String toString() {
		return "PurchaseType [id=" + id + ", name=" + name + ", code=" + code + "]";
	}


}
