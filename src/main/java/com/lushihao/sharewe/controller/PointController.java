package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.sharewe.entity.userinfo.PointExchangeRecord;
import com.lushihao.sharewe.service.PointExchangeRecordService;
import com.lushihao.sharewe.service.PointExchangeService;
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
    private PointExchangeRecordService pointExchangeRecordService;

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

        return pointExchangeRecordService.createPointExchangeRecord(pointExchangeRecord, point);
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

        return pointExchangeRecordService.selectPointExchangeRecord(openId);
    }
}
