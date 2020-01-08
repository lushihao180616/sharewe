package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.userinfo.UserInfo;

public interface UserInfoService {

    String handleGetUserInfo(String code, String encryptedData, String iv);

    String findByOpenId(String openId);

    String updateUserInfo(UserInfo userInfo, boolean deleteAddress);

    String pointIn(String openId, int needPoint);

    String pointOut(String openId, int needPoint);

}
