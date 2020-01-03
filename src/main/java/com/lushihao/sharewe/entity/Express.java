package com.lushihao.sharewe.entity;

import java.util.Date;
import java.util.List;

public class Express {

	/**
	 * ���Ψһ��ʶ
	 */
	private int id;
	/**
	 * ��ַΨһ��ʶ
	 */
	private int addressId;
	/**
	 * ������ڽ�����
	 */
	private int buildingId;
	/**
	 * ���״̬Ψһ��ʶ
	 */
	private int statusId;
	/**
	 * �ͽ�
	 */
	private double reward;
	/**
	 * ������û�Ψһ��ʶ
	 */
	private String sendUserOpenId;
	/**
	 * �ӿ���û�Ψһ��ʶ
	 */
	private String getUserOpenId;
	/**
	 * ����ʱ��
	 */
	private Date deadTime;
	/**
	 * �����ʱ��
	 */
	private Date sendTime;
	/**
	 * �ӿ��ʱ��
	 */
	private Date getTime;
	/**
	 * ������ʱ��
	 */
	private Date endTime;
	/**
	 * �ӿ���û����
	 */
	private boolean getUserComplete;
	/**
	 * �����Ʒ
	 */
	private List<ExpressItem> expressItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public String getSendUserOpenId() {
		return sendUserOpenId;
	}

	public void setSendUserOpenId(String sendUserOpenId) {
		this.sendUserOpenId = sendUserOpenId;
	}

	public String getGetUserOpenId() {
		return getUserOpenId;
	}

	public void setGetUserOpenId(String getUserOpenId) {
		this.getUserOpenId = getUserOpenId;
	}

	public Date getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getGetTime() {
		return getTime;
	}

	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isGetUserComplete() {
		return getUserComplete;
	}

	public void setGetUserComplete(boolean getUserComplete) {
		this.getUserComplete = getUserComplete;
	}

	public List<ExpressItem> getExpressItems() {
		return expressItems;
	}

	public void setExpressItems(List<ExpressItem> expressItems) {
		this.expressItems = expressItems;
	}

}
