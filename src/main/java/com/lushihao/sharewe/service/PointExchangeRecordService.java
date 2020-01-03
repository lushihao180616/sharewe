package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.PointExchangeRecord;

public interface PointExchangeRecordService {

	String createPointExchangeRecord(PointExchangeRecord pointExchangeRecord);

	String selectPointExchangeRecord(String openId);

}
