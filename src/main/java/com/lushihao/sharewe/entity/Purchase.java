package com.lushihao.sharewe.entity;

import java.util.Date;
import java.util.List;

public class Purchase {

	/**
	 * ����Ψһ��ʶ
	 */
	private int id;
	/**
	 * ��������Ψһ��ʶ
	 */
	private int typeId;
	/**
	 * ��ַΨһ��ʶ
	 */
	private int addressId;
	/**
	 * �������ڽ�����
	 */
	private int buildingId;
	/**
	 * ����״̬Ψһ��ʶ
	 */
	private int statusId;
	/**
	 * �ͽ�
	 */
	private double reward;
	/**
	 * �������û�Ψһ��ʶ
	 */
	private String sendUserOpenId;
	/**
	 * �������û�Ψһ��ʶ
	 */
	private String getUserOpenId;
	/**
	 * ����ʱ��
	 */
	private Date deadTime;
	/**
	 * ������ʱ��
	 */
	private Date sendTime;
	/**
	 * ������ʱ��
	 */
	private Date getTime;
	/**
	 * �������ʱ��
	 */
	private Date endTime;
	/**
	 * �������û�ȡ��
	 */
	private boolean sendUserCancle;
	/**
	 * �������û����
	 */
	private boolean getUserComplete;
	/**
	 * ������Ʒ
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

	public Purchase(int id, int typeId, int addressId, int buildingId, int statusId, double reward,
			String sendUserOpenId, String getUserOpenId, Date deadTime, Date sendTime, Date getTime, Date endTime,
			boolean sendUserCancle, boolean getUserComplete, List<PurchaseItem> purchaseItems) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.addressId = addressId;
		this.buildingId = buildingId;
		this.statusId = statusId;
		this.reward = reward;
		this.sendUserOpenId = sendUserOpenId;
		this.getUserOpenId = getUserOpenId;
		this.deadTime = deadTime;
		this.sendTime = sendTime;
		this.getTime = getTime;
		this.endTime = endTime;
		this.sendUserCancle = sendUserCancle;
		this.getUserComplete = getUserComplete;
		this.purchaseItems = purchaseItems;
	}

	public Purchase() {
		super();
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", typeId=" + typeId + ", addressId=" + addressId + ", buildingId=" + buildingId
				+ ", statusId=" + statusId + ", reward=" + reward + ", sendUserOpenId=" + sendUserOpenId
				+ ", getUserOpenId=" + getUserOpenId + ", deadTime=" + deadTime + ", sendTime=" + sendTime
				+ ", getTime=" + getTime + ", endTime=" + endTime + ", sendUserCancle=" + sendUserCancle
				+ ", getUserComplete=" + getUserComplete + ", purchaseItems=" + purchaseItems + "]";
	}

}
