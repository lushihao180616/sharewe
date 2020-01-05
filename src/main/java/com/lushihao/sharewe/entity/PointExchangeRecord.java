package com.lushihao.sharewe.entity;

public class PointExchangeRecord {
	private String pointExchangeId;
	private String openId;
	private String verificationCode;
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

	public PointExchangeRecord(String pointExchangeId, String openId, String verificationCode, boolean ifUsed) {
		super();
		this.pointExchangeId = pointExchangeId;
		this.openId = openId;
		this.verificationCode = verificationCode;
		this.ifUsed = ifUsed;
	}

	public PointExchangeRecord() {
		super();
	}

	@Override
	public String toString() {
		return "PointExchangeRecord [pointExchangeId=" + pointExchangeId + ", openId=" + openId + ", verificationCode="
				+ verificationCode + ", ifUsed=" + ifUsed + "]";
	}

}
