package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.userinfo.Address;

public interface AddressService {

	String createAddress(Address address);

	String updateAddress(Address address);

	String deleteAddress(Address address);
	
}
