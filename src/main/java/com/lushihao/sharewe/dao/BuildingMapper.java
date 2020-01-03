package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.Building;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuildingMapper {
	/**
	 * ͨ��Id��ȡ������
	 */
	Building findById(int id);

	/**
	 * ͨ��ѧУId��ȡ������
	 */
	List<Building> findBySchoolId(int school_id);

	/**
	 * ͨ��ѧУId��ȡ����¥
	 */
	List<Building> findDormitoryBySchoolId(int schoolId);
}
