package com.lushihao.sharewe.entity.express;

public class ExpressEvaluate {

    /**
     * 快递员标识
     */
    private String openId;
    /**
     * 评价分数
     */
    private int grade;
    /**
     * 描述
     */
    private String des;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}
