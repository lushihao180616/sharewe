package com.lushihao.sharewe.enums;

public enum PurchaseStatusEnum {
    STATUS1(1, "待接单"), STATUS2(2, "待送达"), STATUS3(3, "已完成"), STATUS4(4, "已取消");

    private int id;
    private String name;

    PurchaseStatusEnum(int id, String name) {
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
