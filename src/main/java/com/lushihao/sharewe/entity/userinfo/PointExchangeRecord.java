package com.lushihao.sharewe.entity.userinfo;

public class PointExchangeRecord {
	/**
	 * 劵码
	 */
	private String pointExchangeId;
	/**
	 * 用户
	 */
	private String openId;
	/**
	 * 验证码
	 */
	private String verificationCode;
	/**
	 * 是否被使用
	 */
	private boolean ifUsed;

	public String getPointExchangeId() {
		return pointExchangeId;
	}

	public void setPointExchangeId(String pointExchangeId) {
		this.pointExchangeId = pointExchangeId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isIfUsed() {
		return ifUsed;
	}

	public void setIfUsed(boolean ifUsed) {
		this.ifUsed = ifUsed;
	}

}
