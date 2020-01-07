package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.BuildingMapper;
import com.lushihao.sharewe.dao.SchoolMapper;
import com.lushihao.sharewe.entity.userinfo.Building;
import com.lushihao.sharewe.entity.userinfo.School;
import com.lushihao.sharewe.service.SchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableTransactionManagement
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private BuildingMapper buildingMapper;

    /**
     * 通过省份获取学校
     *
     * @param provinceId 省份唯一标识
     * @return
     */
    @Override
    @Transactional
    public String findSchoolByProvinceId(int provinceId) {
        Map<String, Object> map = new HashMap<>();

        List<School> list = schoolMapper.findByProvinceId(provinceId);
        List<Building> dormitoryList = null;
        if (list.size() > 0) {
            dormitoryList = buildingMapper.findDormitoryBySchoolId(list.get(0).getId());
        }
        map.put("school_list", list);
        map.put("dormitory_list", dormitoryList);
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}