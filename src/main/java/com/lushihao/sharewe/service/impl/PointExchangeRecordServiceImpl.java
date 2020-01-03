package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.dao.PointExchangeRecordMapper;
import com.lushihao.sharewe.entity.PointExchangeRecord;
import com.lushihao.sharewe.service.PointExchangeRecordService;
import com.lushihao.sharewe.util.LSHResponseUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PointExchangeRecordServiceImpl implements PointExchangeRecordService {

	@Resource
	private PointExchangeRecordMapper pointExchangeRecordMapper;
	
	public String createPointExchangeRecord(PointExchangeRecord pointExchangeRecord) {
		Map<String,Object> map = new HashMap<>();
		
		pointExchangeRecord.setVerificationCode(UUID.randomUUID().toString().substring(0,8));
		int sql_back = pointExchangeRecordMapper.createPointExchangeRecord(pointExchangeRecord);
		if (sql_back == 0) {
			return LSHResponseUtils.responseParam(false, map);
		} else {
			return LSHResponseUtils.responseParam(true, map);
		}
	}

	@Override
	public String selectPointExchangeRecord(String openId) {
		Map<String, Object> map = new HashMap<>();

		List<Map<String,Object>> list = pointExchangeRecordMapper.selectPointExchangeRecord(openId);
		map.put("record_list", list);
		return LSHResponseUtils.responseParam(true, map);
	}

}
