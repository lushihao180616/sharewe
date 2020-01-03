package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.AllUserInfo;
import com.lushihao.sharewe.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserInfoMapper {

    /**
     * @param ͨ��openId��ȡ�û���Ϣ
     * @return
     */
    AllUserInfo findByOpenId(String openId);

    /**
     * @param �����û���Ϣ
     * @return
     */
    int createUserInfo(UserInfo userInfo);

    /**
     * @param �����û���Ϣ
     * @return
     */
    int updateUserInfo(UserInfo userInfo);
}
