package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.sharewe.entity.userinfo.UserInfo;
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

    /**
     * 保存用户信息（登录）
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
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

    /**
     * 修改用户信息
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/modifyUserInfo")
    public @ResponseBody
    String modifyUserInfo(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        UserInfo userInfo = LSHJsonUtils.json2Bean(data, UserInfo.class);
        boolean deleteAddress = wxRequestJson.getBoolean("deleteAddress");//是否删除地址

        return userInfoService.updateUserInfo(userInfo, deleteAddress);
    }

    /**
     * 重新登录时获取用户信息
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getUserinfoByOpenid")
    public @ResponseBody
    String getUserinfoByOpenid(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String openId = wxRequestJson.getString("openId");

        return userInfoService.findByOpenId(openId);
    }

}
