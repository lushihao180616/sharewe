package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.PointRecordMapper;
import com.lushihao.sharewe.entity.userinfo.Building;
import com.lushihao.sharewe.entity.userinfo.PointRecord;
import com.lushihao.sharewe.entity.userinfo.School;
import com.lushihao.sharewe.service.PointRecordService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointRecordServiceImpl implements PointRecordService {

    @Resource
    private PointRecordMapper pointRecordMapper;

    /**
     * 获取某用户的捎点记录
     *
     * @return
     */
    @Override
    public String getRecords(String openId) {
        Map<String, Object> map = new HashMap<>();

        List<PointRecord> list = pointRecordMapper.getRecords(openId);
        map.put("record_list", list);
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}
