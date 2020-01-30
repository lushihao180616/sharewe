package com.lushihao.sharewe.service.merchant.impl;

import com.lushihao.myutils.collection.LSHMapUtils;
import com.lushihao.myutils.qrcode.LSHQRCodeUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.merchant.MerchantMapper;
import com.lushihao.sharewe.dao.merchant.UserToMerchantMapper;
import com.lushihao.sharewe.dao.userinfo.PointExchangeMapper;
import com.lushihao.sharewe.entity.merchant.AllMerchantType;
import com.lushihao.sharewe.entity.merchant.Merchant;
import com.lushihao.sharewe.entity.merchant.UserToMerchant;
import com.lushihao.sharewe.entity.userinfo.PointExchange;
import com.lushihao.sharewe.entity.yml.ProjectBasicInfo;
import com.lushihao.sharewe.service.merchant.MerchantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

@Service
@EnableTransactionManagement
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;
    @Resource
    private PointExchangeMapper pointExchangeMapper;
    @Resource
    private AllMerchantType allMerchantType;
    @Resource
    private UserToMerchantMapper userToMerchantMapper;
    @Resource
    private ProjectBasicInfo projectBasicInfo;

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

    /**
     * 创建用户商家对照表记录
     *
     * @param openId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse createUserToMerchant(String openId) throws Exception {
        Map<String,Object> map = new HashMap<>();
        UserToMerchant database_userToMerchant = userToMerchantMapper.findOne(openId);
        if (database_userToMerchant != null) {
            map.put("userToMerchant", database_userToMerchant);
            return new LSHResponse(map);
        }
        UserToMerchant userToMerchant = new UserToMerchant();
        userToMerchant.setOpenId(openId);
        userToMerchant.setMerchantCode(UUID.randomUUID().toString().substring(0, 8).toLowerCase());
        userToMerchant.setIfPass(0);
        int sql_back = userToMerchantMapper.createUserToMerchant(userToMerchant);
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("请求失败，请稍后再试");
        } else {
            LSHQRCodeUtils.encode("您的验证码为：\n" + userToMerchant.getMerchantCode() + "\n\n请将以下验证信息发送至邮箱：\nlushihaomb@qq.com\n\n=>验证码<=\n=>营业执照复印件电子版<=\n=>法人身份证复印件电子版<=\n\n\n感谢您的使用，祝您生意兴隆", projectBasicInfo.getQrCodePath() + "QRcode.jpg");
            return new LSHResponse((Map<String, Object>) null);
        }
    }

}
