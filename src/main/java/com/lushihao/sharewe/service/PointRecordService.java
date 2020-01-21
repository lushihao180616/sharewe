package com.lushihao.sharewe.service;

import com.lushihao.myutils.response.vo.LSHResponse;

public interface PointRecordService {

    /**
     * 获取捎点操作日志
     *
     * @return
     */
    LSHResponse getRecords(String openId, int record_lastId);

}
