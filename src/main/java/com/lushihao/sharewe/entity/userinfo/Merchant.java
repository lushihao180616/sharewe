package com.lushihao.sharewe.entity.userinfo;

public class Merchant {
	/**
	 * �̼ұ��
	 */
	private String code;
	/**
	 * �̼�����
	 */
	private String name;
	/**
	 * �̼�ͼ��
	 */
	private String iconSrc;
	/**
	 * �̼ҵ�ַ
	 */
	private String address;

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

	public String getIconSrc() {
		return iconSrc;
	}

	public void setIconSrc(String iconSrc) {
		this.iconSrc = iconSrc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Merchant(String code, String name, String iconSrc, String address) {
		super();
		this.code = code;
		this.name = name;
		this.iconSrc = iconSrc;
		this.address = address;
	}

	public Merchant() {
		super();
	}

	@Override
	public String toString() {
		return "Merchant [code=" + code + ", name=" + name + ", iconSrc=" + iconSrc + ", address=" + address + "]";
	}

}
