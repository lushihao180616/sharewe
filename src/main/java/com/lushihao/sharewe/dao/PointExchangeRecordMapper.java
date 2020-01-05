package com.lushihao.sharewe.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PointExchangeRecordMapper {

    int createPointExchangeRecord(Map map);

    List<Map<String, Object>> selectPointExchangeRecord(String openId);

}
