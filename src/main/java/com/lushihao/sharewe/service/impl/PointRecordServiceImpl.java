package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.collection.LSHMapUtils;
import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.PointRecordMapper;
import com.lushihao.sharewe.entity.purchase.Purchase;
import com.lushihao.sharewe.entity.userinfo.PointRecord;
import com.lushihao.sharewe.enums.PointRecordTypeEnum;
import com.lushihao.sharewe.service.PointRecordService;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PointRecordServiceImpl implements PointRecordService {

    @Resource
    private PointRecordMapper pointRecordMapper;

    /**
     * 获取某用户的捎点记录
     *
     * @return
     */
    @Override
    @Transactional
    public String getRecords(String openId) {
        List<PointRecord> list = pointRecordMapper.getRecords(openId);
        return transform(list);
    }

    /**
     * 转换方法
     * @param recordList
     * @return
     */
    private String transform(List<PointRecord> recordList) {
        List<Object> list = new ArrayList<>();
        for (PointRecord pointRecord : recordList) {
            Map<String, Object> item_map = LSHMapUtils.entityToMap(pointRecord);
            // 获取类型
            item_map.put("recordSourceType", PointRecordTypeEnum.getOne((int) item_map.get("recordSourceType"), null));
            list.add(item_map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("record_list", list);
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}
