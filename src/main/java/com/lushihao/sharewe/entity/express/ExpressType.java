package com.lushihao.sharewe.entity.express;

public class ExpressType {

    /**
     * 名称
     */
    private String name;
    /**
     * 类型代号
     */
    private String code;
    /**
     * 类型代号
     */
    private String des;
    /**
     * 花费捎点
     */
    private String spend;

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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSpend() {
        return spend;
    }

    public void setSpend(String spend) {
        this.spend = spend;
    }

    public ExpressType() {
    }

    public ExpressType(String name, String code, String des, String spend) {
        this.name = name;
        this.code = code;
        this.des = des;
        this.spend = spend;
    }

}
