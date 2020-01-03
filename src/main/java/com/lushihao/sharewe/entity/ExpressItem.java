package com.lushihao.sharewe.entity;

public class ExpressItem {
	/**
	 * ���Ψһ��ʶ
	 */
	private int id;
	/**
	 * �������
	 */
	private String code;
	/**
	 * �������
	 */
	private String name;
	/**
	 * ����ֻ���
	 */
	private String number;
	/**
	 * ��ݱ�ʶ
	 */
	private int expressId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getExpressId() {
		return expressId;
	}

	public void setExpressId(int expressId) {
		this.expressId = expressId;
	}

}
