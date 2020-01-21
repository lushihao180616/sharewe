package com.lushihao.sharewe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.sharewe.entity.confession.ConfessionWall;
import com.lushihao.sharewe.entity.confession.ConfessionWallItem;
import com.lushihao.sharewe.service.ConfessionWallService;
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
@RequestMapping("/confessionWall")
public class ConfessionWallController {

    @Resource
    private ConfessionWallService confessionWallService;

    /**
     * 发送一条告白墙
     *
     * @param request
     * @param response
     * @param data
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/sendWall")
    public @ResponseBody
    String sendWall(HttpServletRequest request, HttpServletResponse response,
                    @RequestBody String data) throws ParseException {
        //处理告白墙信息
        JSONObject wxRequestJson = LSHJsonUtils.string2JsonObj(data);
        ConfessionWall confessionWall = LSHJsonUtils.json2Bean(wxRequestJson, ConfessionWall.class);
        //处理告白墙单元信息
        List<ConfessionWallItem> needInfoList = LSHJsonUtils.json2List(wxRequestJson.getJSONArray("confessionWallItems"), ConfessionWallItem.class);
        confessionWall.setNeedInfoList(needInfoList);
        //发送告白墙
        return LSHResponseUtils.getResponse(confessionWallService.sendWall(confessionWall));
    }

}
