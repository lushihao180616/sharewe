package com.lushihao.sharewe.dao.userinfo;

import com.lushihao.sharewe.entity.userinfo.PointExchange;
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

    /**
     * 获取劵码列表
     *
     * @return
     */
    List<PointExchange> getPointExchangeListByMerchant(String merchantCode);

}
