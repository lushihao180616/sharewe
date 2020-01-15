package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.merchant.Merchant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantMapper {

   Merchant getMerchant(String merchantCode);

}
