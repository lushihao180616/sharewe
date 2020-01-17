package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.collection.LSHMapUtils;
import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.MerchantMapper;
import com.lushihao.sharewe.dao.PointExchangeMapper;
import com.lushihao.sharewe.entity.merchant.AllMerchantType;
import com.lushihao.sharewe.entity.merchant.Merchant;
import com.lushihao.sharewe.entity.merchant.MerchantType;
import com.lushihao.sharewe.entity.userinfo.Building;
import com.lushihao.sharewe.entity.userinfo.PointExchange;
import com.lushihao.sharewe.entity.userinfo.School;
import com.lushihao.sharewe.service.MerchantService;
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
     * 获取所有商家信息（包含劵码）
     *
     * @return
     */
    @Override
    @Transactional
    public String getMerchants() {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> nowMerchantList = new ArrayList<>();
        List<Merchant> merchantList = merchantMapper.getAllMerchant();
        for (Merchant merchant : merchantList) {
            Map<String, Object> mapItem = LSHMapUtils.entityToMap(merchant);
            List<PointExchange> pointExchangeList = new ArrayList<>();
            if (merchant != null) {
                pointExchangeList = pointExchangeMapper.getPointExchangeListByMerchant(merchant.getCode());
            }
            mapItem.put("pointExchange_list", pointExchangeList);
            mapItem.put("types", allMerchantType.getItemByIds((String) mapItem.get("types")));
            nowMerchantList.add(mapItem);
        }
        map.put("merchant_list", nowMerchantList);
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}
