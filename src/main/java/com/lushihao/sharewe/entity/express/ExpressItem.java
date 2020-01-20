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
     * 快递数量
     */
    private int count;
    /**
     * 快递类型集合
     */
    private String typeCode;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

}
