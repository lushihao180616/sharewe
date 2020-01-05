package com.lushihao.sharewe.controller;

import com.lushihao.sharewe.entity.PointExchangeRecord;
import com.lushihao.sharewe.service.PointExchangeRecordService;
import com.lushihao.sharewe.service.PointExchangeService;
import net.sf.json.JSONObject;
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

        JSONObject wxRequestJson = JSONObject.fromObject(data);
        PointExchangeRecord pointExchangeRecord = (PointExchangeRecord) JSONObject.toBean(wxRequestJson,
                PointExchangeRecord.class);

        return pointExchangeRecordService.createPointExchangeRecord(pointExchangeRecord);
    }

    @RequestMapping(value = "/getPointExchangeRecord")
    public @ResponseBody
    String getPointExchangeRecord(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody String data) {

        JSONObject wxRequestJson = JSONObject.fromObject(data);
        String openId = wxRequestJson.getString("openId");

        return pointExchangeRecordService.selectPointExchangeRecord(openId);
    }
}
