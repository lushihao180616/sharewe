package com.lushihao.sharewe.dao.userinfo;

import com.lushihao.sharewe.entity.userinfo.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
