package com.lushihao.sharewe.dao.userinfo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PointExchangeRecordMapper {

    /**
     * 兑换劵码
     *
     * @param map
     * @return
     */
    int createPointExchangeRecord(Map map);

    /**
     * 获取用户兑换的劵码
     *
     * @param openId
     * @return
     */
    List<Map<String, Object>> selectPointExchangeRecord(String openId);

}
