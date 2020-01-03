package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.UserInfo;

public interface UserInfoService {
    /**
     * 处理获取用户信息方法
     */
    String handleGetUserInfo(String code, String encryptedData, String iv);

    /**
     * 通过OpenId获取用户信息
     */
    String findByOpenId(String openId);

    /**
     * 更新数据
     */
    String updateUserInfo(UserInfo userInfo);
}
