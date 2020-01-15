package com.lushihao.sharewe.entity.confession;

import com.lushihao.sharewe.enums.ConfessionWallItemInfoEnum;

import java.util.List;

public class ConfessionWallItem {

    /**
     * 标识
     */
    private int id;
    /**
     * 需要信息集合
     */
    private List<ConfessionWallItemInfoEnum> infoList;
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

    public List<ConfessionWallItemInfoEnum> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<ConfessionWallItemInfoEnum> infoList) {
        this.infoList = infoList;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
