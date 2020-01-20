package com.lushihao.sharewe.entity.express;

import java.util.Date;
import java.util.List;

public class Express {

    /**
     * 唯一标识
     */
    private int id;
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
     * 发送快递者
     */
    private String sendUserOpenId;
    /**
     * 接收快递者
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
     * 结束时间
     */
    private Date endTime;
    /**
     * 发送快递用户支付完成
     */
    private boolean sendUserPay;
    /**
     * 发送快递用户点击取消
     */
    private boolean sendUserCancle;
    /**
     * 接收快递用户点击完成
     */
    private boolean getUserComplete;
    /**
     * 发送快递单元
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

    public boolean isSendUserPay() {
        return sendUserPay;
    }

    public void setSendUserPay(boolean sendUserPay) {
        this.sendUserPay = sendUserPay;
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

    public List<ExpressItem> getExpressItems() {
        return expressItems;
    }

    public void setExpressItems(List<ExpressItem> expressItems) {
        this.expressItems = expressItems;
    }

}
