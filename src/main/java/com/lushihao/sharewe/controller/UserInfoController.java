package com.lushihao.sharewe.controller;

import com.lushihao.sharewe.entity.UserInfo;
import com.lushihao.sharewe.service.UserInfoService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/saveUserInfo")
    public @ResponseBody
    String parseUserInfo(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody String data) {
        // 获取前端传入的参数
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        String code = wxRequestJson.getString("code");
        String encryptedData = wxRequestJson.getString("encryptedData");
        String iv = wxRequestJson.getString("iv");

        String back = userInfoService.handleGetUserInfo(code, encryptedData, iv);
        return back;
    }

    @RequestMapping(value = "/getUserinfoByOpenid")
    public @ResponseBody
    String findUserInfoByOpenId(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody String data) {

        // 获取前端传入的参数
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        String openId = wxRequestJson.getString("openId");

        return userInfoService.findByOpenId(openId);
    }

    @RequestMapping(value = "/modifyUserInfo")
    public @ResponseBody
    String modifyUserInfo(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody String data) {

        // 获取前端传入的参数
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        UserInfo userInfo = (UserInfo) JSONObject.toBean(wxRequestJson, UserInfo.class);

        return userInfoService.updateUserInfo(userInfo);
    }

}
