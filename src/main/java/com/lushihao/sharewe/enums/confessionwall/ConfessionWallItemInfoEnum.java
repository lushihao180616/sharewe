package com.lushihao.sharewe.enums.confessionwall;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public enum ConfessionWallItemInfoEnum {

    GRADE(1, "年级"), CLASS(2, "班级"), NAME(3, "姓名");

    private int id;
    private String name;

    ConfessionWallItemInfoEnum(int id, String name) {
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
    public static ConfessionWallItemInfoEnum getItem(int id, String name) {
        for (ConfessionWallItemInfoEnum confessionWallItemInfoEnum : values()) {
            if (id != 0 && confessionWallItemInfoEnum.getId() != id) {
                continue;
            }
            if (name != null && confessionWallItemInfoEnum.getName() != name) {
                continue;
            }
            return confessionWallItemInfoEnum;
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
        ConfessionWallItemInfoEnum confessionWallItemInfoEnum = getItem(id, name);
        JSONObject jsonObject = getOne(confessionWallItemInfoEnum);
        return jsonObject;
    }

    /**
     * 获取一条数据
     *
     * @param confessionWallItemInfoEnum
     * @return
     */
    public static JSONObject getOne(ConfessionWallItemInfoEnum confessionWallItemInfoEnum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", confessionWallItemInfoEnum.getId());
        jsonObject.put("name", confessionWallItemInfoEnum.getName());
        return jsonObject;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public static JSONArray getAll() {
        JSONArray jsonArray = new JSONArray();
        for (ConfessionWallItemInfoEnum confessionWallItemInfoEnum : values()) {
            jsonArray.add(getOne(confessionWallItemInfoEnum));
        }
        return jsonArray;
    }

}
