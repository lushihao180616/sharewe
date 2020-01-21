package com.lushihao.sharewe.service;

import com.lushihao.myutils.response.vo.LSHResponse;

public interface PointRecordService {

    LSHResponse getRecords(String openId, int record_lastId);

}
