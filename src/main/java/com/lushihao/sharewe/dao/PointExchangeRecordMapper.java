package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.PointExchangeRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PointExchangeRecordMapper {

	int createPointExchangeRecord(PointExchangeRecord pointExchangeRecord);

	List<Map<String,Object>> selectPointExchangeRecord(String openId);

}
