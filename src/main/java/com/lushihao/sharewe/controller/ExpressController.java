package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.entity.express.ExpressItem;
import com.lushihao.sharewe.entity.purchase.Purchase;
import com.lushihao.sharewe.entity.purchase.PurchaseItem;
import com.lushihao.sharewe.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
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
        //处理任务信息
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        String sendTime = wxRequestJson.getString("sendTime");
        String deadTime = wxRequestJson.getString("deadTime");
        wxRequestJson.put("sendTime", LSHDateUtils.string2Date(sendTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        wxRequestJson.put("deadTime", LSHDateUtils.string2Date(deadTime, LSHDateUtils.YYYY_MM_DD_HH_MM_SS2));
        Express express = LSHJsonUtils.json2Bean(wxRequestJson, Express.class);
        //处理任务单元信息
        List<ExpressItem> expressItems_list = LSHJsonUtils.json2List(wxRequestJson.getJSONArray("expressItems"), ExpressItem.class);
        express.setExpressItems(expressItems_list);
        //发送任务
        return expressService.sendExpress(express);
    }

}
