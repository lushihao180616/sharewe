package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.userinfo.PointRecord;

public interface PointRecordService {

    /**
     * 添加捎点日志
     * @param pointRecord
     */
    void addRecord(PointRecord pointRecord);

    /**
     * 获取捎点操作日志
     * @return
     */
    String getRecords();

}
