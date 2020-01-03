package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.Address;

import java.util.List;
import java.util.Map;

public interface AddressService {

	String createAddress(Address address);

	String updateAddress(Address address);

	String deleteAddress(Address address);
	
}
