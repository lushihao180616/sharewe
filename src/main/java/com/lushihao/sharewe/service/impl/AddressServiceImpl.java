package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.dao.AddressMapper;
import com.lushihao.sharewe.dao.BuildingMapper;
import com.lushihao.sharewe.entity.Address;
import com.lushihao.sharewe.service.AddressService;
import com.lushihao.sharewe.util.LSHResponseUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

	@Resource
	private AddressMapper addressMapper;
	@Resource
	private BuildingMapper buildingMapper;

	public String createAddress(Address address) {
		Map<String, Object> map = new HashMap<>();

		int sql_back = addressMapper.createAddress(address);
		if (sql_back == 0) {
			map.put("address_list", null);
			return LSHResponseUtils.responseParam(false, map);
		} else {
			map.put("address_list", findByOpenId(address.getOpenId()));
			return LSHResponseUtils.responseParam(true, map);
		}
	}

	public String updateAddress(Address address) {
		Map<String, Object> map = new HashMap<>();

		int sql_back = addressMapper.updateAddress(address);
		if (sql_back == 0) {
			map.put("address_list", null);
			return LSHResponseUtils.responseParam(false, map);
		} else {
			map.put("address_list", findByOpenId(address.getOpenId()));
			return LSHResponseUtils.responseParam(true, map);
		}
	}

	public String deleteAddress(Address address) {
		Map<String, Object> map = new HashMap<>();

		int sql_back = addressMapper.deleteAddress(address);
		if (sql_back == 0) {
			map.put("address_list", null);
			return LSHResponseUtils.responseParam(false, map);
		} else {
			map.put("address_list", findByOpenId(address.getOpenId()));
			return LSHResponseUtils.responseParam(true, map);
		}
	}

	public List<Map<String, Object>> findByOpenId(String openId) {
		List<Map<String, Object>> address_list = addressMapper.findByOpenId(openId);
		return address_list;
	}
}
