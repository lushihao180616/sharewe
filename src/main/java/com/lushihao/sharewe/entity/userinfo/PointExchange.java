package com.lushihao.sharewe.entity.userinfo;

public class PointExchange {

	/**
	 * 标识
	 */
	private String id;
	/**
	 * 商家代号
	 */
	private String merchantCode;
	/**
	 * 劵码内容
	 */
	private String context;
	/**
	 * 劵码到期时间
	 */
	private String time;
	/**
	 * 兑换需要用的捎点
	 */
	private int point;

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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
