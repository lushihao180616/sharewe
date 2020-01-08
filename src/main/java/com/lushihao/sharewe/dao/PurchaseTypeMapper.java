package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.purchase.PurchaseType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseTypeMapper {

    /**
     * 通过id获取任务类型
     *
     * @param id
     * @return
     */
    PurchaseType findById(int id);

    /**
     * 获取所有任务类型信息
     *
     * @return
     */
    List<PurchaseType> findAll();

}
