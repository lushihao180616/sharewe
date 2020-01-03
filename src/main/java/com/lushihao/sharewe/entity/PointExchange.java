package com.lushihao.sharewe.entity;

public class PointExchange {
	/**
	 * Ψһ��ʶ
	 */
	private String id;
	/**
	 * �̼ұ��
	 */
	private String merchantCode;
	/**
	 * ������
	 */
	private String context;
	/**
	 * ����ֹʱ��
	 */
	private String time;
	/**
	 * ��ֵ�ӵ�
	 */
	private double point;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public PointExchange(String id, String merchantCode, String context, String time, double point) {
		super();
		this.id = id;
		this.merchantCode = merchantCode;
		this.context = context;
		this.time = time;
		this.point = point;
	}

	public PointExchange() {
		super();
	}

}
