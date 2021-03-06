package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.entity.purchase.Purchase;
import com.lushihao.sharewe.entity.purchase.PurchaseEvaluate;
import com.lushihao.sharewe.entity.purchase.PurchaseItem;
import com.lushihao.sharewe.service.purchase.PurchaseService;
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
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    //==================================================发送、接收任务开始==================================================

    /**
     * 发送任务者发送任务（修改任务）
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/sendPurchase")
    public @ResponseBody
    String sendPurchase(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody String data) throws ParseException {
        //处理任务信息
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String sendTime = wxRequestJson.getString("sendTime");
        String deadTime = wxRequestJson.getString("deadTime");
        wxRequestJson.put("sendTime", LSHDateUtils.string2Date(sendTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        wxRequestJson.put("deadTime", LSHDateUtils.string2Date(deadTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        //转换成需要的任务
        Purchase purchase = LSHJsonUtils.json2Bean(wxRequestJson, Purchase.class);
        //处理任务单元信息
        List<PurchaseItem> purchaseItems_list = LSHJsonUtils.json2List(wxRequestJson.getJSONArray("purchaseItems"), PurchaseItem.class);
        purchase.setPurchaseItems(purchaseItems_list);
        //用于更新时，修改地址使用数量
        int originalAddressId = wxRequestJson.getInteger("originalAddressId");
        //发送任务
        return LSHResponseUtils.getResponse(purchaseService.sendPurchase(purchase, originalAddressId));
    }

    /**
     * 接收任务者接收任务页面数据
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getPurchases")
    public @ResponseBody
    String getPurchases(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody String data) {

        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int buildingId = wxRequestJson.getInteger("buildingId");
        int typeId = wxRequestJson.getInteger("typeId");
        int purchase_lastId = wxRequestJson.getInteger("purchase_lastId");
        return LSHResponseUtils.getResponse(purchaseService.getPurchases(buildingId, typeId, purchase_lastId));
    }

    /**
     * 接收任务者接收任务
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getPurchase")
    public @ResponseBody
    String getPurchase(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String getTime = wxRequestJson.getString("getTime");
        try {
            wxRequestJson.put("getTime", LSHDateUtils.string2Date(getTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Purchase purchase = LSHJsonUtils.json2Bean(wxRequestJson, Purchase.class);
        return LSHResponseUtils.getResponse(purchaseService.getPurchase(purchase));
    }

    //==================================================发送、接收任务结束==================================================
    //==================================================任务订单管理开始==================================================

    /**
     * 删除任务
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/removePurchase")
    public @ResponseBody
    String removePurchase(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int purchaseId = wxRequestJson.getInteger("purchaseId");

        return LSHResponseUtils.getResponse(purchaseService.removePurchase(purchaseId));
    }

    /**
     * 发送任务者查看发送任务
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getSendPurchase")
    public @ResponseBody
    String getSendPurchase(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String sendUserOpenId = wxRequestJson.getString("sendUserOpenId");
        int statusId = wxRequestJson.getInteger("statusId");

        return LSHResponseUtils.getResponse(purchaseService.getSendPurchase(sendUserOpenId, statusId));
    }

    /**
     * 接收任务者接收任务页面数据
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getGetPurchase")
    public @ResponseBody
    String getGetPurchase(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String getUserOpenId = wxRequestJson.getString("getUserOpenId");
        int statusId = wxRequestJson.getInteger("statusId");

        return LSHResponseUtils.getResponse(purchaseService.getGetPurchase(getUserOpenId, statusId));
    }

    /**
     * 发送任务者点击取消按钮
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/sendCanclePurchase")
    public @ResponseBody
    String sendCanclePurchase(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int purchaseId = wxRequestJson.getInteger("purchaseId");
        boolean sendUserCancle = wxRequestJson.getBoolean("sendUserCancle");

        return LSHResponseUtils.getResponse(purchaseService.sendCanclePurchase(purchaseId, sendUserCancle));
    }

    /**
     * 接收任务者点击取消按钮
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCanclePurchase")
    public @ResponseBody
    String getCanclePurchase(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int purchaseId = wxRequestJson.getInteger("purchaseId");
        int guarantee = wxRequestJson.getInteger("guarantee");
        String getUserOpenId = wxRequestJson.getString("getUserOpenId");

        return LSHResponseUtils.getResponse(purchaseService.getCanclePurchase(purchaseId, guarantee, getUserOpenId));
    }

    /**
     * 接收任务者点击完成按钮
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCompletePurchase")
    public @ResponseBody
    String getCompletePurchase(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int purchaseId = wxRequestJson.getInteger("purchaseId");
        boolean getUserComplete = wxRequestJson.getBoolean("getUserComplete");

        return LSHResponseUtils.getResponse(purchaseService.getCompletePurchase(purchaseId, getUserComplete));
    }

    /**
     * 发送任务者点击完成按钮
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/sendCompletePurchase")
    public @ResponseBody
    String sendCompletePurchase(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        int purchaseId = wxRequestJson.getInteger("purchaseId");
        int guarantee = wxRequestJson.getInteger("guarantee");
        int reward = wxRequestJson.getInteger("reward");
        String sendUserOpenId = wxRequestJson.getString("sendUserOpenId");
        String getUserOpenId = wxRequestJson.getString("getUserOpenId");
        int addressId = wxRequestJson.getInteger("addressId");

        return LSHResponseUtils.getResponse(purchaseService.sendCompletePurchase(purchaseId, guarantee, reward, sendUserOpenId, getUserOpenId, addressId));
    }

    /**
     * 评价
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/sendPurchaseEvaluate")
    public @ResponseBody
    String sendPurchaseEvaluate(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody String data) {
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        PurchaseEvaluate purchaseEvaluate = LSHJsonUtils.json2Bean(wxRequestJson, PurchaseEvaluate.class);
        int purchaseId = wxRequestJson.getInteger("purchaseId");
        return LSHResponseUtils.getResponse(purchaseService.sendPurchaseEvaluate(purchaseEvaluate, purchaseId));
    }

    //==================================================任务订单管理结束==================================================

}
