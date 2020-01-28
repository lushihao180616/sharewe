package com.lushihao.sharewe.service.userinfo;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.entity.userinfo.UserInfo;
import com.lushihao.sharewe.entity.userinfo.UserProfessionTypeRecord;

public interface UserInfoService {

    LSHResponse handleGetUserInfo(String code, String encryptedData, String iv);

    LSHResponse findByOpenId(String openId);

    LSHResponse updateUserInfo(UserInfo userInfo, boolean deleteAddress);

    LSHResponse pointIn(String openId, int needPoint, int recordSourceType);

    LSHResponse pointOut(String openId, int needPoint, int recordSourceType);

    LSHResponse findUserInfoByOpenId(String openId);

    LSHResponse getAllProfession(String openId);

    LSHResponse changeTypeOpen(UserProfessionTypeRecord userProfessionTypeRecord, int point);
}
