package com.lushihao.sharewe.entity.express;

public class ExpressTypeAndNum {

    /**
     * 快递标识
     */
    private int expressId;
    /**
     * 快递类型标识
     */
    private int typeId;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
