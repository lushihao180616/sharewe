package com.lushihao.sharewe.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
            if (code != null && purchaseTypeEnum.getCode() != code) {
                continue;
            }
            return purchaseTypeEnum;
        }
        return null;
    }

    /**
     * 获取一条数据
     * @param id
     * @param name
     * @param code
     * @return
     */
    public static JSONObject getOne(int id, String name, String code) {
        PurchaseTypeEnum purchaseTypeEnum = getItem(id, name, code);
        JSONObject jsonObject = getOne(purchaseTypeEnum);
        return jsonObject;
    }

    /**
     * 获取一条数据
     *
     * @param purchaseTypeEnum
     * @return
     */
    public static JSONObject getOne(PurchaseTypeEnum purchaseTypeEnum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", purchaseTypeEnum.getId());
        jsonObject.put("name", purchaseTypeEnum.getName());
        jsonObject.put("code", purchaseTypeEnum.getCode());
        return jsonObject;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public static JSONArray getAll() {
        JSONArray jsonArray = new JSONArray();
        for (PurchaseTypeEnum purchaseTypeEnum : values()) {
            jsonArray.add(getOne(purchaseTypeEnum));
        }
        return jsonArray;
    }

}
