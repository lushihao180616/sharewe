package com.lushihao.sharewe.entity.confession;

import com.lushihao.sharewe.entity.common.Image;

import java.util.List;

public class ConfessionWall {

    /**
     * 标识
     */
    private int id;
    /**
     * 发出的用户
     */
    private String sendUserOpenId;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片地址
     */
    private List<Image> imgSrcs;
    /**
     * 需要的信息
     */
    private List<ConfessionWallItem> needInfoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendUserOpenId() {
        return sendUserOpenId;
    }

    public void setSendUserOpenId(String sendUserOpenId) {
        this.sendUserOpenId = sendUserOpenId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Image> getImgSrcs() {
        return imgSrcs;
    }

    public void setImgSrcs(List<Image> imgSrcs) {
        this.imgSrcs = imgSrcs;
    }

    public List<ConfessionWallItem> getNeedInfoList() {
        return needInfoList;
    }

    public void setNeedInfoList(List<ConfessionWallItem> needInfoList) {
        this.needInfoList = needInfoList;
    }

}
