package com.lushihao.sharewe.entity.merchant;

public class UserToMerchant {

    /**
     * 用户标识
     */
    private String openId;
    /**
     * 商家代号
     */
    private String merchantCode;
    /**
     * 是否通过(0:未进行操作，1:通过，-1:不通过)
     */
    private int ifPass;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public int getIfPass() {
        return ifPass;
    }

    public void setIfPass(int ifPass) {
        this.ifPass = ifPass;
    }

}
