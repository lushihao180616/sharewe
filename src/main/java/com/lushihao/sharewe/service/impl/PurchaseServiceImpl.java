package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.dao.*;
import com.lushihao.sharewe.entity.Address;
import com.lushihao.sharewe.entity.Purchase;
import com.lushihao.sharewe.entity.PurchaseItem;
import com.lushihao.sharewe.service.PurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@EnableTransactionManagement
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private BuildingMapper buildingMapper;
    @Resource
    private PurchaseTypeMapper purchaseTypeMapper;
    @Resource
    private PurchaseStatusMapper purchaseStatusMapper;
    @Resource
    private PurchaseItemMapper purchaseItemMapper;

    @Override
    @Transactional
    public String sendPurchase(Purchase purchase) {
        int sql_back;
        if (purchase.getId() == 0) {
            sql_back = purchaseMapper.createPurchase(purchase);
            if (sql_back > 0) {
                addressMapper.updateAddressUsedCount(purchase.getAddressId());
            }
        } else {
            sql_back = purchaseMapper.updatePurchase(purchase);
        }
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            for (PurchaseItem purchaseItem : purchase.getPurchaseItems()) {
                purchaseItem.setPurchaseId(purchase.getId());
            }
            if (purchase.getId() != 0) {
                purchaseItemMapper.batchDeletePurchaseItems(purchase.getId());
            }
            int batch_sql_back = purchaseItemMapper.batchCreatePurchaseItems(purchase.getPurchaseItems());
            if (batch_sql_back == purchase.getPurchaseItems().size()) {
                return LSHResponseUtils.getResponse(new LSHResponse((String) null));
            } else {
                return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
            }
        }
    }

    @Override
    @Transactional
    public String getPurchases(int num, int page) {
        Date lastGetDate = new Date();
        lastGetDate.setMinutes(lastGetDate.getMinutes() + 5);
        List<Purchase> purchase_list = purchaseMapper.findPurchases(num, (page - 1) * num, lastGetDate);

        return transform(purchase_list);
    }

    @Override
    @Transactional
    public String getPurchase(Purchase purchase) {
        int sql_back = purchaseMapper.getPurchase(purchase);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    @Override
    @Transactional
    public String filterPurchases(int num, int page, int buildingId, int typeId) {
        Date lastGetDate = new Date();
        lastGetDate.setMinutes(lastGetDate.getMinutes() + 5);
        List<Purchase> purchase_list = purchaseMapper.filterPurchases(num, (page - 1) * num, lastGetDate, buildingId,
                typeId);

        return transform(purchase_list);
    }

    @Override
    @Transactional
    public String getSendPurchase(String sendUserOpenId, int statusId) {
        List<Purchase> purchase_list = purchaseMapper.getSendPurchase(sendUserOpenId, statusId);
        return transform(purchase_list);
    }

    @Override
    @Transactional
    public String getGetPurchase(String getUserOpenId, int statusId) {
        List<Purchase> purchase_list = purchaseMapper.getGetPurchase(getUserOpenId, statusId);
        return transform(purchase_list);
    }

    @Override
    @Transactional
    public String romovePurchase(int purchaseId) {
        int sql_back1 = purchaseItemMapper.batchDeletePurchaseItems(purchaseId);
        int sql_back2 = purchaseMapper.deletePurchase(purchaseId);
        if (sql_back1 == 0 || sql_back2 == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    @Override
    @Transactional
    public String sendCanclePurchase(int purchaseId, boolean sendUserCancle) {
        int sql_back = purchaseMapper.sendCanclePurchase(purchaseId, sendUserCancle);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    @Override
    @Transactional
    public String getCanclePurchase(int purchaseId) {
        int sql_back = purchaseMapper.getCanclePurchase(purchaseId);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    @Override
    @Transactional
    public String getCompletePurchase(int purchaseId, boolean getUserComplete) {
        int sql_back = purchaseMapper.getCompletePurchase(purchaseId, getUserComplete);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    @Override
    @Transactional
    public String sendCompletePurchase(int purchaseId) {
        int sql_back = purchaseMapper.sendCompletePurchase(purchaseId);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    @Transactional
    String transform(List<Purchase> purchase_list) {
        List<Object> list = new ArrayList<>();
        Map<String, Object> item_map;
        for (Purchase purchase : purchase_list) {
            item_map = new HashMap<>();

            Address address = addressMapper.findById(purchase.getAddressId());
            List<PurchaseItem> purchase_items = purchaseItemMapper.findPurchaseItemsByPurchaseId(purchase.getId());
            item_map.put("id", purchase.getId());
            item_map.put("type", purchaseTypeMapper.findById(purchase.getTypeId()));
            item_map.put("address", address);
            item_map.put("building", buildingMapper.findById(address.getBuilding_id()));
            item_map.put("status", purchaseStatusMapper.findById(purchase.getStatusId()));
            item_map.put("reward", purchase.getReward());
            item_map.put("sendUserInfo", userInfoMapper.findByOpenId(purchase.getSendUserOpenId()));
            if (purchase.getGetUserOpenId() != null) {
                item_map.put("getUserInfo", userInfoMapper.findByOpenId(purchase.getGetUserOpenId()));
            }
            item_map.put("deadTime", LSHDateUtils.date2String(purchase.getDeadTime(), LSHDateUtils.YYYY_MM_DD_HH_MM_SS1));
            item_map.put("sendTime", LSHDateUtils.date2String(purchase.getSendTime(), LSHDateUtils.YYYY_MM_DD_HH_MM_SS1));
            if (purchase.getGetTime() != null) {
                item_map.put("getTime", LSHDateUtils.date2String(purchase.getGetTime(), LSHDateUtils.YYYY_MM_DD_HH_MM_SS1));
            }
            item_map.put("purchaseItems", purchase_items);
            item_map.put("sendUserCancle", purchase.isSendUserCancle());
            item_map.put("getUserComplete", purchase.isGetUserComplete());
            list.add(item_map);
        }
        if (purchase_list.size() <= 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("没有数据"));
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("purchase_list", list);
            return LSHResponseUtils.getResponse(new LSHResponse(map));
        }
    }

}
