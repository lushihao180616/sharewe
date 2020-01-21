package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.sharewe.entity.userinfo.Address;
import com.lushihao.sharewe.entity.userinfo.UserInfo;
import com.lushihao.sharewe.service.userinfo.AddressService;
import com.lushihao.sharewe.service.userinfo.BuildingService;
import com.lushihao.sharewe.service.userinfo.SchoolService;
import com.lushihao.sharewe.service.userinfo.UserInfoService;
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
    @Resource
    private AddressService addressService;
    @Resource
    private BuildingService buildingService;
    @Resource
    private SchoolService schoolService;

    //==================================================用户开始==================================================

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

        return LSHResponseUtils.getResponse(userInfoService.handleGetUserInfo(code, encryptedData, iv));
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

        return LSHResponseUtils.getResponse(userInfoService.updateUserInfo(userInfo, deleteAddress));
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

        return LSHResponseUtils.getResponse(userInfoService.findByOpenId(openId));
    }

    //==================================================用户结束==================================================
    //==================================================学校开始==================================================

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

        return LSHResponseUtils.getResponse(schoolService.findSchoolByProvinceId(provinceId));
    }

    //==================================================学校结束==================================================
    //==================================================建筑开始==================================================

    /**
     * 获取学校下的宿舍楼数据
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getDormitory")
    public @ResponseBody
    String getDormitory(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int schoolId = wxRequestJson.getInteger("schoolId");

        return LSHResponseUtils.getResponse(buildingService.findDormitoryBySchoolId(schoolId));
    }

    //==================================================建筑结束==================================================
    //==================================================地址开始==================================================

    /**
     * 保存地址
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/saveAddress")
    public @ResponseBody
    String saveAddress(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody String data) {
        Address address = LSHJsonUtils.json2Bean(data, Address.class);

        return LSHResponseUtils.getResponse(addressService.createAddress(address));
    }

    /**
     * 修改地址
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/modifyAddress")
    public @ResponseBody
    String modifyAddress(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody String data) {
        Address address = LSHJsonUtils.json2Bean(data, Address.class);

        return LSHResponseUtils.getResponse(addressService.updateAddress(address));
    }

    /**
     * 删除地址
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/deleteAddress")
    public @ResponseBody
    String deleteAddress(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody String data) {
        Address address = LSHJsonUtils.json2Bean(data, Address.class);

        return LSHResponseUtils.getResponse(addressService.deleteAddress(address));
    }

    //==================================================地址结束==================================================

}
