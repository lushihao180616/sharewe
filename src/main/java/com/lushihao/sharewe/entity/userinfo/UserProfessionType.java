package com.lushihao.sharewe.entity.userinfo;

public class UserProfessionType {

    /**
     * 名称
     */
    private String name;
    /**
     * 代号
     */
    private String code;
    /**
     * 描述
     */
    private String des;
    /**
     * 捎点
     */
    private int point;

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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public UserProfessionType() {
    }

    public UserProfessionType(String name, String code, String des) {
        this.name = name;
        this.code = code;
        this.des = des;
    }

    public UserProfessionType(String name, String code, String des, int point) {
        this.name = name;
        this.code = code;
        this.des = des;
        this.point = point;
    }

}
