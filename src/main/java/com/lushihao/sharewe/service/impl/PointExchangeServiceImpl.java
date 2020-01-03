package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.PointExchangeMapper;
import com.lushihao.sharewe.service.PointExchangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PointExchangeServiceImpl implements PointExchangeService {

	@Resource
	private PointExchangeMapper pointExchangeDao;

	public String getPointExchangeList() {
		List<Map<String,Object>> pointExchangeList = pointExchangeDao.getPointExchangeList();

		Map<String, Object> map = new HashMap<>();
		map.put("pointExchange_list", pointExchangeList);
		return LSHResponseUtils.getResponse(new LSHResponse(map));
	}

}