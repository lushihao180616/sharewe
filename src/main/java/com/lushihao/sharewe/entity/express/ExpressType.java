package com.lushihao.sharewe.entity.express;

public class ExpressType {

    /**
     * 标识
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型代号
     */
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ExpressType() {
    }

    public ExpressType(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

}
