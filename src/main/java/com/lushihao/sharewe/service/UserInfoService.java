package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.userinfo.UserInfo;

public interface UserInfoService {
    /**
     * 处理获取用户信息方法
     *
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     */
    String handleGetUserInfo(String code, String encryptedData, String iv);

    /**
     * 通过OpenId获取用户信息
     *
     * @param openId
     * @return
     */
    String findByOpenId(String openId);

    /**
     * 更新数据
     *
     * @param userInfo
     * @param deleteAddress
     * @return
     */
    String updateUserInfo(UserInfo userInfo, boolean deleteAddress);

    /**
     * 添加捎点
     *
     * @param openId
     * @param needPoint
     * @return
     */
    String pointIn(String openId, int needPoint);

    /**
     * 提现捎点
     *
     * @param openId
     * @param needPoint
     * @return
     */
    String pointOut(String openId, int needPoint);
}
