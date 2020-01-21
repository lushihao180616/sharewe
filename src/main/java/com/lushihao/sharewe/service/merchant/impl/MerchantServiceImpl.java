package com.lushihao.sharewe.service.merchant.impl;

import com.lushihao.myutils.collection.LSHMapUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.merchant.MerchantMapper;
import com.lushihao.sharewe.dao.userinfo.PointExchangeMapper;
import com.lushihao.sharewe.entity.merchant.AllMerchantType;
import com.lushihao.sharewe.entity.merchant.Merchant;
import com.lushihao.sharewe.entity.userinfo.PointExchange;
import com.lushihao.sharewe.service.merchant.MerchantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableTransactionManagement
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;
    @Resource
    private PointExchangeMapper pointExchangeMapper;
    @Resource
    private AllMerchantType allMerchantType;

    /**
     * 获取商家信息（包含劵码）
     *
     * @param merchantCode
     * @return
     */
    @Override
    @Transactional
    public LSHResponse getMerchantInfo(String merchantCode) {
        Map<String, Object> map = new HashMap<>();

        Merchant merchant = merchantMapper.getMerchant(merchantCode);
        List<PointExchange> pointExchangeList = new ArrayList<>();
        if (merchant != null) {
            pointExchangeList = pointExchangeMapper.getPointExchangeListByMerchant(merchantCode);
        }
        Map<String, Object> mapItem = LSHMapUtils.entityToMap(merchant);
        mapItem.put("pointExchange_list", pointExchangeList);
        mapItem.put("types", allMerchantType.getItemByIds((String) mapItem.get("types")));
        map.put("merchant", mapItem);
        return new LSHResponse(map);
    }

    /**
     * 获取所有商家信息（包含劵码）
     *
     * @return
     */
    @Override
    @Transactional
    public LSHResponse getMerchants() {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> nowMerchantList = new ArrayList<>();
        List<Merchant> merchantList = merchantMapper.getAllMerchant();
        for (Merchant merchant : merchantList) {
            Map<String, Object> mapItem = LSHMapUtils.entityToMap(merchant);
            mapItem.put("types", allMerchantType.getItemByIds((String) mapItem.get("types")));
            nowMerchantList.add(mapItem);
        }
        map.put("merchant_list", nowMerchantList);
        return new LSHResponse(map);
    }

}
