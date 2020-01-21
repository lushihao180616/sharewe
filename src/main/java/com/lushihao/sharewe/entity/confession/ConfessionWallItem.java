package com.lushihao.sharewe.entity.confession;

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
    /**
     * 告白墙标识
     */
    private int wallId;

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

    public int getWallId() {
        return wallId;
    }

    public void setWallId(int wallId) {
        this.wallId = wallId;
    }

}
