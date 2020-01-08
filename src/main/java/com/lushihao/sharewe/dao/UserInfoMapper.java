package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.userinfo.AllUserInfo;
import com.lushihao.sharewe.entity.userinfo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {

    /**
     * 获取用户信息
     *
     * @param openId
     * @return
     */
    AllUserInfo findByOpenId(String openId);

    /**
     * 创建用户信息
     *
     * @param userInfo
     * @return
     */
    int createUserInfo(UserInfo userInfo);

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 捎点添加
     *
     * @param openId
     * @param needPoint
     * @return
     */
    int pointIn(String openId, int needPoint);

    /**
     * 捎点提现
     *
     * @param openId
     * @param needPoint
     * @return
     */
    int pointOut(String openId, int needPoint);

}
