package com.lushihao.sharewe.enums.point;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public enum PointRecordTypeEnum {

    TYPE_MONEY_IN(101, "充值"),
    TYPE_MONEY_OUT(102, "提现"),
    TYPE_PURCHASE_SEND(201, "发送任务"),
    TYPE_PURCHASE_GET(202, "接收任务"),
    TYPE_PURCHASE_CANCLE(203, "取消任务"),
    TYPE_PURCHASE_REMOVE(204, "删除任务"),
    TYPE_PURCHASE_RESEND(205, "重新发送任务"),
    TYPE_PURCHASE_SEND_COMPLETE(206, "发送任务被完成"),
    TYPE_PURCHASE_GET_COMPLETE(207, "完成接收任务"),
    TYPE_EXPRESS_SEND(301, "发送快递"),
    TYPE_EXPRESS_CANCLE(302, "取消快递"),
    TYPE_EXPRESS_REMOVE(303, "删除快递"),
    TYPE_EXPRESS_SEND_COMPLETE(304, "发送快递被完成"),
    TYPE_EXPRESS_GET_COMPLETE(305, "完成接收快递"),
    TYPE_EXPRESS_SEND_PAY(306, "支付快递赏金"),
    TYPE_POINTEXCHANGE_EXCHANGE(401, "兑换劵码");

    private int id;
    private String content;

    PointRecordTypeEnum(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 通过属性获取对象
     *
     * @param id
     * @return
     */
    public static PointRecordTypeEnum getItem(int id, String content) {
        for (PointRecordTypeEnum pointRecordTypeEnum : values()) {
            if (id != 0 && pointRecordTypeEnum.getId() != id) {
                continue;
            }
            if (content != null && pointRecordTypeEnum.getContent() != content) {
                continue;
            }
            return pointRecordTypeEnum;
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
        PointRecordTypeEnum pointRecordTypeEnum = getItem(id, name);
        JSONObject jsonObject = getOne(pointRecordTypeEnum);
        return jsonObject;
    }

    /**
     * 获取一条数据
     *
     * @param pointRecordTypeEnum
     * @return
     */
    public static JSONObject getOne(PointRecordTypeEnum pointRecordTypeEnum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", pointRecordTypeEnum.getId());
        jsonObject.put("name", pointRecordTypeEnum.getContent());
        return jsonObject;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public static JSONArray getAll() {
        JSONArray jsonArray = new JSONArray();
        for (PointRecordTypeEnum pointRecordTypeEnum : values()) {
            jsonArray.add(getOne(pointRecordTypeEnum));
        }
        return jsonArray;
    }

}
