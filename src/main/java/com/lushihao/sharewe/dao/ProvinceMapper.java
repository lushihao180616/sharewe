package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProvinceMapper {
	/**
	 * ͨ��Id��ȡʡ��
	 */
	Province findById(int id);

	/**
	 * ͨ��Code��ȡʡ��
	 */
	Province findByCode(String code);

	/**
	 * ��ȡ���е�ʡ��
	 */
	List<Province> findAll();
}
