package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.dao.SchoolMapper;
import com.lushihao.sharewe.entity.School;
import com.lushihao.sharewe.service.SchoolService;
import com.lushihao.sharewe.util.LSHResponseUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Resource
	private SchoolMapper schoolDao;

	public String findSchoolByProvinceId(int provinceId) {
		Map<String, Object> map = new HashMap<>();

		List<School> list = schoolDao.findByProvinceId(provinceId);
		map.put("school_list", list);
		return LSHResponseUtils.responseParam(true, map);
	}

}