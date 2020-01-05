package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface SchoolMapper {
    School findById(int id);

    List<School> findByProvinceId(int province_id);

    List<School> findAll();

}
