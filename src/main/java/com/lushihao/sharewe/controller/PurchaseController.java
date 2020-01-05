package com.lushihao.sharewe.controller;

import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.entity.Purchase;
import com.lushihao.sharewe.entity.PurchaseItem;
import com.lushihao.sharewe.service.PurchaseService;
import com.lushihao.sharewe.service.PurchaseTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;
    @Resource
    private PurchaseTypeService purchaseTypeService;

    @RequestMapping(value = "/sendPurchase")
    public @ResponseBody
    String sendPurchase(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody String data) {
        List<PurchaseItem> purchaseItems_list = new ArrayList<>();
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        String sendTime = wxRequestJson.getString("sendTime");
        String deadTime = wxRequestJson.getString("deadTime");
        JSONArray purchaseItems = wxRequestJson.getJSONArray("purchaseItems");
        for (int i = 0; i < purchaseItems.size(); i++) {
            JSONObject json = purchaseItems.getJSONObject(i);
            json.remove("name_num");
            PurchaseItem purchaseItem = (PurchaseItem) JSONObject.toBean(json, PurchaseItem.class);
            purchaseItems_list.add(purchaseItem);
        }
        wxRequestJson.remove("purchaseItems");
        try {
            wxRequestJson.put("sendTime", LSHDateUtils.string2Date(sendTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
            wxRequestJson.put("deadTime", LSHDateUtils.string2Date(deadTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Purchase purchase = (Purchase) JSONObject.toBean(wxRequestJson, Purchase.class);
        purchase.setPurchaseItems(purchaseItems_list);
        return purchaseService.sendPurchase(purchase);
    }

    @RequestMapping(value = "/getPurchases")
    public @ResponseBody
    String getPurchases(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        int num = wxRequestJson.getInt("num");
        int page = wxRequestJson.getInt("page");
        return purchaseService.getPurchases(num, page);
    }

    @RequestMapping(value = "/getPurchase")
    public @ResponseBody
    String getPurchase(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        String getTime = wxRequestJson.getString("getTime");
        try {
            wxRequestJson.put("getTime", LSHDateUtils.string2Date(getTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Purchase purchase = (Purchase) JSONObject.toBean(wxRequestJson, Purchase.class);
        return purchaseService.getPurchase(purchase);
    }

    @RequestMapping(value = "/filterPurchases")
    public @ResponseBody
    String filterPurchases(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        int num = wxRequestJson.getInt("num");
        int page = wxRequestJson.getInt("page");
        int buildingId = wxRequestJson.getInt("buildingId");
        int typeId = wxRequestJson.getInt("typeId");

        return purchaseService.filterPurchases(num, page, buildingId, typeId);
    }

    @RequestMapping(value = "/getSendPurchase")
    public @ResponseBody
    String getSendPurchase(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        String sendUserOpenId = wxRequestJson.getString("sendUserOpenId");
        int statusId = wxRequestJson.getInt("statusId");

        return purchaseService.getSendPurchase(sendUserOpenId, statusId);
    }

    @RequestMapping(value = "/getGetPurchase")
    public @ResponseBody
    String getGetPurchase(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        String getUserOpenId = wxRequestJson.getString("getUserOpenId");
        int statusId = wxRequestJson.getInt("statusId");

        return purchaseService.getGetPurchase(getUserOpenId, statusId);
    }

    @RequestMapping(value = "/removePurchase")
    public @ResponseBody
    String removePurchase(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        int purchaseId = wxRequestJson.getInt("purchaseId");

        return purchaseService.romovePurchase(purchaseId);
    }

    @RequestMapping(value = "/sendCanclePurchase")
    public @ResponseBody
    String sendCanclePurchase(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        int purchaseId = wxRequestJson.getInt("purchaseId");
        boolean sendUserCancle = wxRequestJson.getBoolean("sendUserCancle");

        return purchaseService.sendCanclePurchase(purchaseId, sendUserCancle);
    }

    @RequestMapping(value = "/getCanclePurchase")
    public @ResponseBody
    String getCanclePurchase(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        int purchaseId = wxRequestJson.getInt("purchaseId");

        return purchaseService.getCanclePurchase(purchaseId);
    }

    @RequestMapping(value = "/getCompletePurchase")
    public @ResponseBody
    String getCompletePurchase(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        int purchaseId = wxRequestJson.getInt("purchaseId");
        boolean getUserComplete = wxRequestJson.getBoolean("getUserComplete");

        return purchaseService.getCompletePurchase(purchaseId, getUserComplete);
    }

    @RequestMapping(value = "/sendCompletePurchase")
    public @ResponseBody
    String sendCompletePurchase(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody String data) {
        JSONObject wxRequestJson = JSONObject.fromObject(data);
        int purchaseId = wxRequestJson.getInt("purchaseId");

        return purchaseService.sendCompletePurchase(purchaseId);
    }

    @RequestMapping(value = "/getPurchaseTypes")
    public @ResponseBody
    String getPurchaseTypes(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody String data) {

        return purchaseTypeService.findAllPurchaseTypes();
    }
}
