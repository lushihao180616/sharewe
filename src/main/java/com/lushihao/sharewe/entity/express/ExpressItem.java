package com.lushihao.sharewe.entity.express;

public class ExpressItem {

    /**
     * 标识
     */
    private int id;
    /**
     * 验证码
     */
    private String code;
    /**
     * 昵称
     */
    private String name;
    /**
     * 手机尾号
     */
    private String number;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
