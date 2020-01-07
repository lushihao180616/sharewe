package com.lushihao.sharewe.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PointExchangeMapper {

    /**
     * 获取劵码列表
     *
     * @return
     */
    List<Map<String, Object>> getPointExchangeList();

}
