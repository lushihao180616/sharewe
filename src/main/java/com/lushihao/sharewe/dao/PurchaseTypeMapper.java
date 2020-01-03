package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.PurchaseType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseTypeMapper {
	/**
	 * ͨ��Id��ȡʡ��
	 */
	PurchaseType findById(int id);

	/**
	 * ��ȡ���е�ʡ��
	 */
	List<PurchaseType> findAll();

}
