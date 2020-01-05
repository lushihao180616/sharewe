package com.lushihao.sharewe.controller;

import com.lushihao.sharewe.entity.Address;
import com.lushihao.sharewe.service.AddressService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Resource
	private AddressService addressService;
	
	@RequestMapping(value = "/saveAddress")
	public @ResponseBody String saveAddress(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String data) {
		
		JSONObject wxRequestJson = JSONObject.fromObject(data);
		Address address = (Address)JSONObject.toBean(wxRequestJson, Address.class);
		
		return addressService.createAddress(address);
	}
	
	@RequestMapping(value = "/modifyAddress")
	public @ResponseBody String modifyAddress(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String data) {
		
		JSONObject wxRequestJson = JSONObject.fromObject(data);
		Address address = (Address)JSONObject.toBean(wxRequestJson, Address.class);
		
		return addressService.updateAddress(address);
	}
	@RequestMapping(value = "/deleteAddress")
	public @ResponseBody String deleteAddress(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String data) {
		
		JSONObject wxRequestJson = JSONObject.fromObject(data);
		Address address = (Address)JSONObject.toBean(wxRequestJson, Address.class);
		
		return addressService.deleteAddress(address);
	}
}
