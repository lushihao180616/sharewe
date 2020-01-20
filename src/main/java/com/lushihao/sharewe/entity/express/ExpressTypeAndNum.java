package com.lushihao.sharewe.entity.express;

public class ExpressTypeAndNum {

    /**
     * 快递标识
     */
    private int expressId;
    /**
     * 快递类型标识
     */
    private String typeCode;
    /**
     * 快递数量
     */
    private int num;

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
