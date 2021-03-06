package com.lushihao.sharewe.service.userinfo.impl;

import com.lushihao.myutils.collection.LSHMapUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.dao.userinfo.PointRecordMapper;
import com.lushihao.sharewe.entity.userinfo.PointRecord;
import com.lushihao.sharewe.enums.point.PointRecordStatusEnum;
import com.lushihao.sharewe.enums.point.PointRecordTypeEnum;
import com.lushihao.sharewe.service.userinfo.PointRecordService;
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
    public LSHResponse getRecords(String openId, int record_lastId) {
        List<PointRecord> list = pointRecordMapper.getRecords(openId, record_lastId);
        return transform(list);
    }

    /**
     * 转换方法
     *
     * @param recordList
     * @return
     */
    private LSHResponse transform(List<PointRecord> recordList) {
        List<Object> list = new ArrayList<>();
        for (PointRecord pointRecord : recordList) {
            Map<String, Object> item_map = LSHMapUtils.entityToMap(pointRecord);
            // 获取类型
            item_map.put("recordSourceType", PointRecordTypeEnum.getOne((int) item_map.get("recordSourceType"), null));
            item_map.put("flag", PointRecordStatusEnum.getOne((int) item_map.get("flag"), null));
            item_map.put("saveTime", LSHDateUtils.date2String(pointRecord.getSaveTime(), LSHDateUtils.YYYY_MM_DD_HH_MM_SS1));
            list.add(item_map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("record_list", list);
        return new LSHResponse(map);
    }

}
