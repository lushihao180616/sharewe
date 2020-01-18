package com.lushihao.sharewe.entity.express;

public class ExpressItem {

    /**
     * 标识
     */
    private int id;
    /**
     * 快递位置
     */
    private String address;
    /**
     * 快递类型
     */
    private int expressType;
    /**
     * 快递
     */
    private int expressId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getExpressType() {
        return expressType;
    }

    public void setExpressType(int expressType) {
        this.expressType = expressType;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

}
