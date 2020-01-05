package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.sharewe.entity.UserInfo;
import com.lushihao.sharewe.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/saveUserInfo")
    public @ResponseBody
    String saveUserInfo(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody String data) {

        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String code = wxRequestJson.getString("code");
        String encryptedData = wxRequestJson.getString("encryptedData");
        String iv = wxRequestJson.getString("iv");

        return userInfoService.handleGetUserInfo(code, encryptedData, iv);
    }

    @RequestMapping(value = "/getUserinfoByOpenid")
    public @ResponseBody
    String findUserInfoByOpenId(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody String data) {

        // 获取前端传入的参数
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String openId = wxRequestJson.getString("openId");

        return userInfoService.findByOpenId(openId);
    }

    @RequestMapping(value = "/modifyUserInfo")
    public @ResponseBody
    String modifyUserInfo(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody String data) {

        // 获取前端传入的参数
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        UserInfo userInfo = LSHJsonUtils.json2Bean(data, UserInfo.class);
        boolean deleteAddress = wxRequestJson.getBoolean("deleteAddress");

        return userInfoService.updateUserInfo(userInfo, deleteAddress);
    }

}
