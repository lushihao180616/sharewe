package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.BuildingMapper;
import com.lushihao.sharewe.entity.userinfo.Building;
import com.lushihao.sharewe.service.BuildingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableTransactionManagement
public class BuildingServiceImpl implements BuildingService {

    @Resource
    private BuildingMapper buildingMapper;

    /**
     * 获取学校下的宿舍楼数据
     * @param schoolId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse findDormitoryBySchoolId(int schoolId) {
        Map<String, Object> map = new HashMap<>();

        List<Building> list = buildingMapper.findDormitoryBySchoolId(schoolId);
        map.put("dormitory_list", list);
        return new LSHResponse(map);
    }

}
