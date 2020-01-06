package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressMapper {
	Address findById(int id);

	List<Map<String,Object>> findByOpenId(String openId);

	int createAddress(Address address);

	int updateAddress(Address address);

	int deleteAddress(Address address);

	int deleteAllAddress(String openId);

    int updateAddressUsedCount(int id);
}
