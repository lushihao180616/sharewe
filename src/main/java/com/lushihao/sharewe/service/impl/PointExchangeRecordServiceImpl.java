package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.collection.LSHMapUtils;
import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.PointExchangeRecordMapper;
import com.lushihao.sharewe.entity.PointExchangeRecord;
import com.lushihao.sharewe.service.PointExchangeRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@EnableTransactionManagement
public class PointExchangeRecordServiceImpl implements PointExchangeRecordService {

    @Resource
    private PointExchangeRecordMapper pointExchangeRecordMapper;

    /**
     * 兑换劵码
     *
     * @param pointExchangeRecord
     * @param point
     * @return
     */
    @Override
    @Transactional
    public String createPointExchangeRecord(PointExchangeRecord pointExchangeRecord, int point) {
        Map<String, Object> map = new HashMap<>();

        pointExchangeRecord.setVerificationCode(UUID.randomUUID().toString().substring(0, 6));
        Map<String, Object> param = LSHMapUtils.entityToMap(pointExchangeRecord);
        param.put("point", point);
        int sql_back = pointExchangeRecordMapper.createPointExchangeRecord(param);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse(map));
        }
    }

    /**
     * 获取我兑换的劵码
     *
     * @param openId
     * @return
     */
    @Override
    @Transactional
    public String selectPointExchangeRecord(String openId) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> list = pointExchangeRecordMapper.selectPointExchangeRecord(openId);
        map.put("record_list", list);
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}
