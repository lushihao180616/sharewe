package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.entity.express.ExpressEvaluate;
import com.lushihao.sharewe.entity.express.ExpressItem;
import com.lushihao.sharewe.entity.express.ExpressTypeAndNum;
import com.lushihao.sharewe.service.express.ExpressService;
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

    //==================================================发送、接收快递开始==================================================

    /**
     * 发送快递
     *
     * @param request
     * @param response
     * @param data
     * @returngetExpress
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
        //用于更新时，修改地址使用数量
        int originalAddressId = wxRequestJson.getInteger("originalAddressId");
        //发送快递
        return LSHResponseUtils.getResponse(expressService.sendExpress(express, originalAddressId));
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
        return LSHResponseUtils.getResponse(expressService.getExpresses(buildingId, express_lastId));
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
        return LSHResponseUtils.getResponse(expressService.getExpress(express));
    }

    //==================================================发送、接收快递结束==================================================
    //==================================================快递订单管理开始==================================================

    /**
     * 快递员发送需要支付的赏金
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/sendExpressReward")
    public @ResponseBody
    String sendExpressReward(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        Express express = LSHJsonUtils.json2Bean(wxRequestJson, Express.class);
        //处理快递单元信息
        List<ExpressTypeAndNum> expressTypeAndNums_list = LSHJsonUtils.json2List(wxRequestJson.getJSONArray("expressTypeAndNums"), ExpressTypeAndNum.class);
        express.setExpressTypeAndNums(expressTypeAndNums_list);

        return LSHResponseUtils.getResponse(expressService.sendExpressReward(express));
    }

    /**
     * 发快递人支付赏金
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/payExpressReward")
    public @ResponseBody
    String payExpressReward(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        Express express = LSHJsonUtils.json2Bean(wxRequestJson, Express.class);

        return LSHResponseUtils.getResponse(expressService.payExpressReward(express));
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

        return LSHResponseUtils.getResponse(expressService.removeExpress(expressId));
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

        return LSHResponseUtils.getResponse(expressService.getSendExpress(sendUserOpenId, statusId));
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

        return LSHResponseUtils.getResponse(expressService.getGetExpress(getUserOpenId, statusId));
    }

    /**
     * 发送快递者点击取消按钮
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/sendCancleExpress")
    public @ResponseBody
    String sendCancleExpress(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int expressId = wxRequestJson.getInteger("expressId");
        boolean sendUserCancle = wxRequestJson.getBoolean("sendUserCancle");

        return LSHResponseUtils.getResponse(expressService.sendCancleExpress(expressId, sendUserCancle));
    }

    /**
     * 接收快递者点击取消按钮
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCancleExpress")
    public @ResponseBody
    String getCancleExpress(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int expressId = wxRequestJson.getInteger("expressId");
        String getUserOpenId = wxRequestJson.getString("getUserOpenId");

        return LSHResponseUtils.getResponse(expressService.getCancleExpress(expressId, getUserOpenId));
    }

    /**
     * 接收快递者点击完成按钮
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCompleteExpress")
    public @ResponseBody
    String getCompleteExpress(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int expressId = wxRequestJson.getInteger("expressId");
        boolean getUserComplete = wxRequestJson.getBoolean("getUserComplete");

        return LSHResponseUtils.getResponse(expressService.getCompleteExpress(expressId, getUserComplete));
    }

    /**
     * 发送任务者点击完成按钮
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/sendCompleteExpress")
    public @ResponseBody
    String sendCompleteExpress(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int expressId = wxRequestJson.getInteger("expressId");
        int reward = wxRequestJson.getInteger("reward");
        String sendUserOpenId = wxRequestJson.getString("sendUserOpenId");
        String getUserOpenId = wxRequestJson.getString("getUserOpenId");
        int addressId = wxRequestJson.getInteger("addressId");

        return LSHResponseUtils.getResponse(expressService.sendCompleteExpress(expressId, reward, sendUserOpenId, getUserOpenId, addressId));
    }

    /**
     * 评价
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/sendExpressEvaluate")
    public @ResponseBody
    String sendExpressEvaluate(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        ExpressEvaluate expressEvaluate = LSHJsonUtils.json2Bean(wxRequestJson, ExpressEvaluate.class);
        int expressId = wxRequestJson.getInteger("expressId");
        return LSHResponseUtils.getResponse(expressService.sendExpressEvaluate(expressEvaluate, expressId));
    }

    //==================================================快递订单管理结束==================================================

}
