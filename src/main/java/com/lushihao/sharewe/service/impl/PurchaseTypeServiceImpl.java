package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.dao.PurchaseTypeMapper;
import com.lushihao.sharewe.entity.PurchaseType;
import com.lushihao.sharewe.service.PurchaseTypeService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseTypeServiceImpl implements PurchaseTypeService {
	
	@Resource
	PurchaseTypeMapper purchaseTypeMapper;
	
	public JSONArray findAllPurchaseTypes() {
		List<PurchaseType> list = purchaseTypeMapper.findAll();
		JSONArray backArray = JSONArray.fromObject(list);
		return backArray;
	}
}