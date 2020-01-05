package com.lushihao.sharewe.controller;

import com.lushihao.sharewe.service.PurchaseTypeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/purchasetype")
public class PurchaseTypeController {

	@Resource
	private PurchaseTypeService purchaseTypeService;
	
	@RequestMapping(value = "/getpurchasetypes")
	public @ResponseBody String getPurchaseTypes(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String data) {
		
		return purchaseTypeService.findAllPurchaseTypes().toString();
	}

}
