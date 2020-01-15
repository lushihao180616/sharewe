package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.userinfo.PointRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointRecordMapper {

    /**
     * 创建捎点日志
     *
     * @param pointRecord
     * @return
     */
    int createPointRecord(PointRecord pointRecord);

    /**
     * 获取捎点操作日志集合
     *
     * @param openId
     * @return
     */
    List<PointRecord> getRecords(String openId);

}
