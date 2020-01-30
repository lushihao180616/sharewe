package com.lushihao.sharewe.dao.merchant;

import com.lushihao.sharewe.entity.merchant.UserToMerchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserToMerchantMapper {

    int createUserToMerchant(UserToMerchant userToMerchant);

    UserToMerchant findOne(@Param("openId") String openId);

}
