package com.lushihao.sharewe.enums;

public enum PurchaseStatusEnum {

    WAITING_GET(1, "待接单"), WAITING_SERVED(2, "待送达"), ALREADY_DONE(3, "已完成"), ALREADY_CANCLE(4, "已取消"), TIME_OUT(5, "已超时");

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

    /**
     * 通过属性获取对象
     *
     * @param id
     * @return
     */
    public static PurchaseStatusEnum getItem(int id, String name) {
        for (PurchaseStatusEnum purchaseStatusEnum : values()) {
            if (id != 0 && purchaseStatusEnum.getId() != id) {
                continue;
            }
            if (name != null && purchaseStatusEnum.getName() != name) {
                continue;
            }
            return purchaseStatusEnum;
        }
        return null;
    }

}
