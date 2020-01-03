package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressMapper {
	/**
	 * ͨ��Id��ȡ��ַ
	 */
	Address findById(int id);

	/**
	 * ͨ��openId��ȡ��ַ
	 */
	List<Map<String,Object>> findByOpenId(String openId);

	/**
	 * ������ַ
	 */
	int createAddress(Address address);

	/**
	 * ���µ�ַ
	 * @return
	 */
	int updateAddress(Address address);

	/**
	 * ɾ����ַ
	 */
	int deleteAddress(Address address);
}
