package com.lushihao.sharewe.service;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.entity.userinfo.Address;

public interface AddressService {

	LSHResponse createAddress(Address address);

	LSHResponse updateAddress(Address address);

	LSHResponse deleteAddress(Address address);
	
}
