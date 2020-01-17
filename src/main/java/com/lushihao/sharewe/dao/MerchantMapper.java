package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.merchant.Merchant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MerchantMapper {

   Merchant getMerchant(String merchantCode);

   List<Merchant> getAllMerchant();

}
