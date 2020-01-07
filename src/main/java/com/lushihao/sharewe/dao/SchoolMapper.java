package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface SchoolMapper {
    /**
     * 通过省份id获取学校
     *
     * @param province_id
     * @return
     */
    List<School> findByProvinceId(int province_id);
}
