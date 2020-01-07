package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.PurchaseTypeMapper;
import com.lushihao.sharewe.entity.PurchaseType;
import com.lushihao.sharewe.service.PurchaseTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableTransactionManagement
public class PurchaseTypeServiceImpl implements PurchaseTypeService {
	
	@Resource
	PurchaseTypeMapper purchaseTypeMapper;

	@Override
	@Transactional
	public String findAllPurchaseTypes() {
		Map<String, Object> map = new HashMap<>();
		List<PurchaseType> list = purchaseTypeMapper.findAll();
		map.put("purchaseType_list", list);
		return LSHResponseUtils.getResponse(new LSHResponse(map));
	}
}