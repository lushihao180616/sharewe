package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.qrcode.LSHQRCodeUtils;
import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.sharewe.service.merchant.MerchantService;
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
    @RequestMapping(value = "/getMerchantInfo")
    public @ResponseBody
    String getMerchantInfo(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String merchantCode = wxRequestJson.getString("merchantCode");

        return LSHResponseUtils.getResponse(merchantService.getMerchantInfo(merchantCode));
    }

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
        return LSHResponseUtils.getResponse(merchantService.getMerchants());
    }

    /**
     * 创建商家认证记录
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/createUserToMerchant")
    public @ResponseBody
    String createUserToMerchant(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody String data) throws Exception {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String openId = wxRequestJson.getString("openId");
        return LSHResponseUtils.getResponse(merchantService.createUserToMerchant(openId));
    }

}
