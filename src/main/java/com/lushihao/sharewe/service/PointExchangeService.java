package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.userinfo.PointExchangeRecord;

public interface PointExchangeService {

	String getPointExchangeList();

	String createPointExchangeRecord(PointExchangeRecord pointExchangeRecord, int point);

	String selectPointExchangeRecord(String openId);

}
