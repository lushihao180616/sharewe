package com.lushihao.sharewe.entity;

public class PurchaseItem {
	/**
	 * ����Ψһ��ʶ
	 */
	private int id;
	/**
	 * ��Ʒ����
	 */
	private String name;
	/**
	 * ��Ʒ����
	 */
	private int num;
	/**
	 * ��ע
	 */
	private String remarks;
	/**
	 * �����ʶ
	 */
	private int purchaseId;

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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public PurchaseItem(int id, String name, int num, String remarks, int purchaseId) {
		super();
		this.id = id;
		this.name = name;
		this.num = num;
		this.remarks = remarks;
		this.purchaseId = purchaseId;
	}

	public PurchaseItem() {
		super();
	}

	@Override
	public String toString() {
		return "PurchaseItem [id=" + id + ", name=" + name + ", num=" + num + ", remarks=" + remarks + ", purchaseId="
				+ purchaseId + "]";
	}

}
