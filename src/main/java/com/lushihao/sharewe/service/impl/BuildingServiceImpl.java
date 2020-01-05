package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.BuildingMapper;
import com.lushihao.sharewe.entity.Building;
import com.lushihao.sharewe.service.BuildingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Resource
    private BuildingMapper buildingMapper;

    public String findDormitoryBySchoolId(int schoolId) {
        Map<String, Object> map = new HashMap<>();

        List<Building> list = buildingMapper.findDormitoryBySchoolId(schoolId);
        map.put("dormitory_list", list);
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}
