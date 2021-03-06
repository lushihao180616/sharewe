package com.lushihao.sharewe.entity.userinfo;

import java.util.Date;

public class PointRecord {

    /**
     * 标识
     */
    private int id;
    /**
     * 用户标识
     */
    private String openId;
    /**
     * 来源（1：充值/提现，2：任务，3：快递，4：劵码）
     */
    private int recordSourceType;
    /**
     * 保存时间
     */
    private Date saveTime;
    /**
     * 数量
     */
    private int num;
    /**
     * 标识（1：增加，2：减少）
     */
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getRecordSourceType() {
        return recordSourceType;
    }

    public void setRecordSourceType(int recordSourceType) {
        this.recordSourceType = recordSourceType;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public PointRecord(int id) {
    }

    public PointRecord(int id, String openId, int recordSourceType, Date saveTime, int num, int flag) {
        this.id = id;
        this.openId = openId;
        this.recordSourceType = recordSourceType;
        this.saveTime = saveTime;
        this.num = num;
        this.flag = flag;
    }

    public PointRecord(String openId, int recordSourceType, int num, int flag) {
        this.openId = openId;
        this.recordSourceType = recordSourceType;
        this.saveTime = new Date();
        this.num = num;
        this.flag = flag;
    }
}
