package com.lushihao.sharewe.enums;

public enum PurchaseTypeEnum {

    TYPE1(1, "美食", "food");

    private int id;
    private String name;
    private String code;

    PurchaseTypeEnum(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 通过属性获取对象
     *
     * @param id
     * @return
     */
    public static PurchaseTypeEnum getItem(int id, String name, String code) {
        for (PurchaseTypeEnum purchaseTypeEnum : values()) {
            if (id != 0 && purchaseTypeEnum.getId() != id) {
                continue;
            }
            if (name != null && purchaseTypeEnum.getName() != name) {
                continue;
            }
            if (code != null && purchaseTypeEnum.getName() != code) {
                continue;
            }
            return purchaseTypeEnum;
        }
        return null;
    }

}
