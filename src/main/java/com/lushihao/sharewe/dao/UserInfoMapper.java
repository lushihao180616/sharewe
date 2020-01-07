package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.AllUserInfo;
import com.lushihao.sharewe.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
