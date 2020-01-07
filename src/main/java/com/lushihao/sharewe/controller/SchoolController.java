package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.sharewe.service.SchoolService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    /**
     * 通过省份获取学校
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getByProvince")
    public @ResponseBody
    String getByProvince(HttpServletRequest request, HttpServletResponse response, @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int provinceId = wxRequestJson.getInteger("provinceId");

        return schoolService.findSchoolByProvinceId(provinceId);
    }
}
