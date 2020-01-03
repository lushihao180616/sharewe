package com.lushihao.sharewe.controller;

import com.lushihao.sharewe.service.SchoolService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("/school")
public class SchoolController {

	@Resource
	private SchoolService schoolService;
	
	@RequestMapping(value = "/getByProvince")
	public @ResponseBody String userinfo(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String data) {
		
		// ��ȡǰ�˴���Ĳ���
		JSONObject wxRequestJson = JSONObject.fromObject(data);
		int provinceId = wxRequestJson.getInt("provinceId");
		
		return schoolService.findSchoolByProvinceId(provinceId);
	}
}
