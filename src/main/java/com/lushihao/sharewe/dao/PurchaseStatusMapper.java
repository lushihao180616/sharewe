package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.PurchaseStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseStatusMapper {
	PurchaseStatus findById(int id);

	List<PurchaseStatus> findAll();
}
