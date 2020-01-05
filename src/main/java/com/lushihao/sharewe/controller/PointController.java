package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.sharewe.entity.PointExchangeRecord;
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

    @RequestMapping(value = "/pointExchangeList")
    public @ResponseBody
    String getPointExchangeList(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody String data) {

        return pointExchangeService.getPointExchangeList();
    }

    @RequestMapping(value = "/setPointExchangeRecord")
    public @ResponseBody
    String setPointExchangeRecord(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody String data) {

        PointExchangeRecord pointExchangeRecord = LSHJsonUtils.json2Bean(data,
                PointExchangeRecord.class);

        return pointExchangeRecordService.createPointExchangeRecord(pointExchangeRecord);
    }

    @RequestMapping(value = "/getPointExchangeRecord")
    public @ResponseBody
    String getPointExchangeRecord(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody String data) {

        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String openId = wxRequestJson.getString("openId");

        return pointExchangeRecordService.selectPointExchangeRecord(openId);
    }
}
