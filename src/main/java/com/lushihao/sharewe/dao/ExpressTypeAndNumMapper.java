package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.express.ExpressTypeAndNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpressTypeAndNumMapper {

    /**
     * ͨ通过快递获取快递类型列表
     */
    List<ExpressTypeAndNum> findExpressTypeAndNumByExpressId(@Param("expressId") int expressId);

}
