package com.lushihao.sharewe.service.userinfo;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.entity.userinfo.PointExchangeRecord;

public interface PointExchangeService {

	LSHResponse getPointExchangeList();

	LSHResponse createPointExchangeRecord(PointExchangeRecord pointExchangeRecord, int point);

	LSHResponse selectPointExchangeRecord(String openId);

}
