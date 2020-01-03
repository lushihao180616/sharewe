package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.dao.ProvinceMapper;
import com.lushihao.sharewe.entity.Province;
import com.lushihao.sharewe.service.ProvinceService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceMapper provinceDao;

    public JSONArray findAllProvince() {
        List<Province> list = provinceDao.findAll();
        JSONArray backArray = JSONArray.fromObject(list);
        return backArray;
    }

}