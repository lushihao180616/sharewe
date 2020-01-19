package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.entity.express.ExpressItem;
import com.lushihao.sharewe.service.ExpressService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/express")
public class ExpressController {

    @Resource
    private ExpressService expressService;

    /**
     * 发送快递
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/sendExpress")
    public @ResponseBody
    String sendExpress(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody String data) throws ParseException {
        //处理快递信息
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String sendTime = wxRequestJson.getString("sendTime");
        String deadTime = wxRequestJson.getString("deadTime");
        wxRequestJson.put("sendTime", LSHDateUtils.string2Date(sendTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        wxRequestJson.put("deadTime", LSHDateUtils.string2Date(deadTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        Express express = LSHJsonUtils.json2Bean(wxRequestJson, Express.class);
        //处理快递单元信息
        List<ExpressItem> expressItems_list = LSHJsonUtils.json2List(wxRequestJson.getJSONArray("expressItems"), ExpressItem.class);
        express.setExpressItems(expressItems_list);
        //发送快递
        return expressService.sendExpress(express);
    }

    /**
     * 接收快递者接收快递页面数据
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getExpresses")
    public @ResponseBody
    String getExpresses(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody String data) {

        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int buildingId = wxRequestJson.getInteger("buildingId");
        int express_lastId = wxRequestJson.getInteger("express_lastId");
        return expressService.getExpresses(buildingId, express_lastId);
    }

    /**
     * 接收快递者接收快递页面数据
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getExpress")
    public @ResponseBody
    String getExpress(HttpServletRequest request, HttpServletResponse response,
                      @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String getTime = wxRequestJson.getString("getTime");
        try {
            wxRequestJson.put("getTime", LSHDateUtils.string2Date(getTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Express express = LSHJsonUtils.json2Bean(wxRequestJson, Express.class);
        return expressService.getExpress(express);
    }

    /**
     * 删除快递
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/removeExpress")
    public @ResponseBody
    String removeExpress(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int expressId = wxRequestJson.getInteger("expressId");

        return expressService.removeExpress(expressId);
    }

    /**
     * 发送快递者查看快递任务
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getSendExpress")
    public @ResponseBody
    String getSendExpress(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String sendUserOpenId = wxRequestJson.getString("sendUserOpenId");
        int statusId = wxRequestJson.getInteger("statusId");

        return expressService.getSendExpress(sendUserOpenId, statusId);
    }

    /**
     * 接收快递者接收快递页面数据
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getGetExpress")
    public @ResponseBody
    String getGetExpress(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String getUserOpenId = wxRequestJson.getString("getUserOpenId");
        int statusId = wxRequestJson.getInteger("statusId");

        return expressService.getGetExpress(getUserOpenId, statusId);
    }

}
