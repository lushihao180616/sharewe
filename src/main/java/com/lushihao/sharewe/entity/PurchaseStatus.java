package com.lushihao.sharewe.entity;

public class PurchaseStatus {
	private int id;
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

	public PurchaseStatus(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public PurchaseStatus() {
		super();
	}

	@Override
	public String toString() {
		return "PurchaseStatus [id=" + id + ", name=" + name + "]";
	}

}
