package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SchoolMapper {
    /**
     * ͨ��Id��ȡʡ��
     */
    School findById(int id);

    /**
     * ͨ��Code��ȡʡ��
     */
    List<School> findByProvinceId(int province_id);

    /**
     * ��ȡ���е�ʡ��
     */
    List<School> findAll();

}
