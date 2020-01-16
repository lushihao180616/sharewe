package com.lushihao.sharewe.enums.point;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public enum PointRecordStatusEnum {

    WAITING_GET(1, "+"), WAITING_SERVED(2, "-");

    private int id;
    private String name;

    PointRecordStatusEnum(int id, String name) {
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
    public static PointRecordStatusEnum getItem(int id, String name) {
        for (PointRecordStatusEnum pointRecordStatusEnum : values()) {
            if (id != 0 && pointRecordStatusEnum.getId() != id) {
                continue;
            }
            if (name != null && pointRecordStatusEnum.getName() != name) {
                continue;
            }
            return pointRecordStatusEnum;
        }
        return null;
    }

    /**
     * 获取一条数据
     *
     * @param id
     * @param name
     * @return
     */
    public static JSONObject getOne(int id, String name) {
        PointRecordStatusEnum pointRecordStatusEnum = getItem(id, name);
        JSONObject jsonObject = getOne(pointRecordStatusEnum);
        return jsonObject;
    }

    /**
     * 获取一条数据
     *
     * @param pointRecordStatusEnum
     * @return
     */
    public static JSONObject getOne(PointRecordStatusEnum pointRecordStatusEnum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", pointRecordStatusEnum.getId());
        jsonObject.put("name", pointRecordStatusEnum.getName());
        return jsonObject;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public static JSONArray getAll() {
        JSONArray jsonArray = new JSONArray();
        for (PointRecordStatusEnum pointRecordStatusEnum : values()) {
            jsonArray.add(getOne(pointRecordStatusEnum));
        }
        return jsonArray;
    }

}
