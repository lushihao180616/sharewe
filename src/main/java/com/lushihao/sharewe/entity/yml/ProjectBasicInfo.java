package com.lushihao.sharewe.entity.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "basic")  // 配置文件中的前缀
public class ProjectBasicInfo {

    /**
     * 项目名称（中文）
     */
    private String chineseName;
    /**
     * 项目名称（英文）
     */
    private String englishName;
    /**
     * 任务提前几分钟被认为超时
     */
    private int purchaseAdvanceMinute;
    /**
     * 快递提前几分钟被认为超时
     */
    private int expressAdvanceMinute;
    /**
     * 二维码地址
     */
    private String qrCodePath;

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public int getPurchaseAdvanceMinute() {
        return purchaseAdvanceMinute;
    }

    public void setPurchaseAdvanceMinute(int purchaseAdvanceMinute) {
        this.purchaseAdvanceMinute = purchaseAdvanceMinute;
    }

    public int getExpressAdvanceMinute() {
        return expressAdvanceMinute;
    }

    public void setExpressAdvanceMinute(int expressAdvanceMinute) {
        this.expressAdvanceMinute = expressAdvanceMinute;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

}
