package com.lushihao.sharewe.controller;

import com.lushihao.sharewe.service.MerchantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

	@Resource
	private MerchantService merchantService;
	
}
