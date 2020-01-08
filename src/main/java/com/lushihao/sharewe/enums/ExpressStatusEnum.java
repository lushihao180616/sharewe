package com.lushihao.sharewe.enums;

public enum ExpressStatusEnum {

    WAITING_GET(1, "待接单"), WAITING_SERVED(2, "待送达"), ALREADY_DONE(3, "已完成"), ALREADY_CANCLE(4, "已取消"), TIME_OUT(5, "已超时");

    private int id;
    private String name;

    ExpressStatusEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

}
