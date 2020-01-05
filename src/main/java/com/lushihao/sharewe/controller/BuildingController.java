package com.lushihao.sharewe.controller;

import com.lushihao.sharewe.service.BuildingService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/building")
public class BuildingController {

	@Resource
	private BuildingService buildingService;
	
	@RequestMapping(value = "/getbyschool")
	public @ResponseBody String getBuilding(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String data) {
		
		JSONObject wxRequestJson = JSONObject.fromObject(data);
		int school_id = wxRequestJson.getInt("school_id");
		
		return buildingService.findBySchoolId(school_id).toString();
	}
	
	@RequestMapping(value = "/getDormitory")
	public @ResponseBody String getDormitory(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String data) {
		
		JSONObject wxRequestJson = JSONObject.fromObject(data);
		int schoolId = wxRequestJson.getInt("schoolId");
		
		return buildingService.findDormitoryBySchoolId(schoolId);
	}
}
