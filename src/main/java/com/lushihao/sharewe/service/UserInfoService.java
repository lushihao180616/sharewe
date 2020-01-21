package com.lushihao.sharewe.service;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.entity.userinfo.UserInfo;

public interface UserInfoService {

    LSHResponse handleGetUserInfo(String code, String encryptedData, String iv);

    LSHResponse findByOpenId(String openId);

    LSHResponse updateUserInfo(UserInfo userInfo, boolean deleteAddress);

    LSHResponse pointIn(String openId, int needPoint, int recordSourceType);

    LSHResponse pointOut(String openId, int needPoint, int recordSourceType);

    LSHResponse findUserInfoByOpenId(String openId);

}
