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
}
