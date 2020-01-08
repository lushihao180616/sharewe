package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.purchase.PurchaseStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseStatusMapper {

	/**
	 * 通过标识获取状态
	 * @param id
	 * @return
	 */
	PurchaseStatus findById(int id);

}
