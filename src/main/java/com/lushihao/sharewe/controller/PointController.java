package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.sharewe.entity.userinfo.PointExchangeRecord;
import com.lushihao.sharewe.enums.PointRecordTypeEnum;
import com.lushihao.sharewe.service.PointExchangeService;
import com.lushihao.sharewe.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/point")
public class PointController {

    @Resource
    private PointExchangeService pointExchangeService;
    @Resource
    private UserInfoService userInfoService;

    //==================================================捎点加减开始==================================================

    /**
     * 添加捎点
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/pointIn")
    public @ResponseBody
    String pointIn(HttpServletRequest request, HttpServletResponse response,
                   @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String openId = wxRequestJson.getString("openId");
        int needPoint = wxRequestJson.getInteger("needPoint");

        return userInfoService.pointIn(openId, needPoint, PointRecordTypeEnum.TYPE_MONEY_IN.getId());
    }

    /**
     * 提现捎点
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/pointOut")
    public @ResponseBody
    String pointOut(HttpServletRequest request, HttpServletResponse response,
                    @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String openId = wxRequestJson.getString("openId");
        int needPoint = wxRequestJson.getInteger("needPoint");

        return userInfoService.pointOut(openId, needPoint, PointRecordTypeEnum.TYPE_MONEY_OUT.getId());
    }

    //==================================================捎点加减结束==================================================
    //==================================================劵码相关开始==================================================

    /**
     * 获取所有的兑换劵码列表
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/pointExchangeList")
    public @ResponseBody
    String pointExchangeList(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody String data) {
        return pointExchangeService.getPointExchangeList();
    }

    /**
     * 兑换劵码
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/setPointExchangeRecord")
    public @ResponseBody
    String setPointExchangeRecord(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody String data) {
        JSONObject jsonObject = LSHJsonUtils.string2JsonObj(data);
        PointExchangeRecord pointExchangeRecord = LSHJsonUtils.json2Bean(data,
                PointExchangeRecord.class);
        int point = jsonObject.getInteger("point");

        return pointExchangeService.createPointExchangeRecord(pointExchangeRecord, point);
    }

    /**
     * 获取我兑换的劵码
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getPointExchangeRecord")
    public @ResponseBody
    String getPointExchangeRecord(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String openId = wxRequestJson.getString("openId");

        return pointExchangeService.selectPointExchangeRecord(openId);
    }

    //==================================================劵码相关结束==================================================

}
