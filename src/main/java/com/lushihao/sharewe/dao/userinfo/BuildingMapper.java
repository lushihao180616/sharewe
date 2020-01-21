package com.lushihao.sharewe.dao.userinfo;

import com.lushihao.sharewe.entity.userinfo.Building;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuildingMapper {

    /**
     * 通过建筑物id获取建筑物
     *
     * @param id
     * @return
     */
    Building findById(int id);

    /**
     * 通过学校获取宿舍
     *
     * @param schoolId
     * @return
     */
    List<Building> findDormitoryBySchoolId(int schoolId);

}
