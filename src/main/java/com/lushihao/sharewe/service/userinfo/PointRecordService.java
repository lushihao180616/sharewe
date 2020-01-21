package com.lushihao.sharewe.service.userinfo;

import com.lushihao.myutils.response.vo.LSHResponse;

public interface PointRecordService {

    LSHResponse getRecords(String openId, int record_lastId);

}
