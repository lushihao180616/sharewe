package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.sharewe.service.MerchantService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Resource
    private MerchantService merchantService;

    /**
     * 获取商家信息（包含劵码）
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getMerchants")
    public @ResponseBody
    String getMerchants(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody String data) {
        return merchantService.getMerchants();
    }

}
