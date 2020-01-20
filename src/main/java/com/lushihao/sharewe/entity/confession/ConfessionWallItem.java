package com.lushihao.sharewe.entity.confession;

import com.lushihao.sharewe.enums.confessionwall.ConfessionWallItemInfoEnum;

public class ConfessionWallItem {

    /**
     * 标识
     */
    private int id;
    /**
     * 需要信息
     */
    private int infoId;
    /**
     * 捎点
     */
    private int point;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
