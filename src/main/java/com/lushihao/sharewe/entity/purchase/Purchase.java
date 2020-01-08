package com.lushihao.sharewe.entity.purchase;

import java.util.Date;
import java.util.List;

public class Purchase {

	/**
	 * 标识
	 */
	private int id;
	/**
	 * 类型
	 */
	private int typeId;
	/**
	 * 地址
	 */
	private int addressId;
	/**
	 * 建筑物
	 */
	private int buildingId;
	/**
	 * 状态
	 */
	private int statusId;
	/**
	 * 赏金
	 */
	private double reward;
	/**
	 * 发送任务者
	 */
	private String sendUserOpenId;
	/**
	 * 接收任务者
	 */
	private String getUserOpenId;
	/**
	 * 限期
	 */
	private Date deadTime;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 接收时间
	 */
	private Date getTime;
	/**
	 * 完成时间
	 */
	private Date endTime;
	/**
	 * 发送任务用户点击取消
	 */
	private boolean sendUserCancle;
	/**
	 * 接收任务用户点击完成
	 */
	private boolean getUserComplete;
	/**
	 * 任务单元
	 */
	private List<PurchaseItem> purchaseItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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

	public boolean isSendUserCancle() {
		return sendUserCancle;
	}

	public void setSendUserCancle(boolean sendUserCancle) {
		this.sendUserCancle = sendUserCancle;
	}

	public boolean isGetUserComplete() {
		return getUserComplete;
	}

	public void setGetUserComplete(boolean getUserComplete) {
		this.getUserComplete = getUserComplete;
	}

	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}

}
