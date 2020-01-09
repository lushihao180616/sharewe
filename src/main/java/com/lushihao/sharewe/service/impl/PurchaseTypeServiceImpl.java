package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.enums.PurchaseTypeEnum;
import com.lushihao.sharewe.service.PurchaseTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableTransactionManagement
public class PurchaseTypeServiceImpl implements PurchaseTypeService {

    /**
     * 获取任务类型列表
     *
     * @return
     */
    @Override
    @Transactional
    public String findAllPurchaseTypes() {
        Map<String, Object> map = new HashMap<>();
        map.put("purchaseType_list", PurchaseTypeEnum.values());
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}