package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.PurchaseStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseStatusMapper {
	/**
	 * 通过标识获取状态
	 * @param id
	 * @return
	 */
	PurchaseStatus findById(int id);

}
