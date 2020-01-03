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

import javax.annotation.Resource;
import java.util.*;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseMapper purchaseDao;
    @Resource
    private AddressMapper addressDao;
    @Resource
    private UserInfoMapper userInfoDao;
    @Resource
    private BuildingMapper buildingDao;
    @Resource
    private PurchaseTypeMapper purchaseTypeDao;
    @Resource
    private PurchaseStatusMapper purchaseStatusDao;
    @Resource
    private PurchaseItemMapper purchaseItemDao;

    public String sendPurchase(Purchase purchase) {
        int sql_back;
        if (purchase.getId() == 0) {
            sql_back = purchaseDao.createPurchase(purchase);
        } else {
            sql_back = purchaseDao.updatePurchase(purchase);
        }
        if (sql_back == 0) {
			return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            for (PurchaseItem purchaseItem : purchase.getPurchaseItems()) {
                purchaseItem.setPurchaseId(purchase.getId());
            }
            if (purchase.getId() != 0) {
                purchaseItemDao.batchDeletePurchaseItems(purchase.getId());
            }
            int batch_sql_back = purchaseItemDao.batchCreatePurchaseItems(purchase.getPurchaseItems());
            if (batch_sql_back == purchase.getPurchaseItems().size()) {
				return LSHResponseUtils.getResponse(new LSHResponse((String) null));
            } else {
				return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
            }
        }
    }

    @SuppressWarnings("deprecation")
    public String getPurchases(int num, int page) {
        Date lastGetDate = new Date();
        lastGetDate.setMinutes(lastGetDate.getMinutes() + 5);
        List<Purchase> purchase_list = purchaseDao.findPurchases(num, (page - 1) * num, lastGetDate);

        return transform(purchase_list);
    }

    public String getPurchase(Purchase purchase) {
        int sql_back = purchaseDao.getPurchase(purchase);
        if (sql_back == 0) {
			return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
			return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    @SuppressWarnings("deprecation")
    public String filterPurchases(int num, int page, int buildingId, int typeId) {
        Date lastGetDate = new Date();
        lastGetDate.setMinutes(lastGetDate.getMinutes() + 5);
        List<Purchase> purchase_list = purchaseDao.filterPurchases(num, (page - 1) * num, lastGetDate, buildingId,
                typeId);

        return transform(purchase_list);
    }

    public String getSendPurchase(String sendUserOpenId, int statusId) {
        List<Purchase> purchase_list = purchaseDao.getSendPurchase(sendUserOpenId, statusId);
        return transform(purchase_list);
    }

    public String getGetPurchase(String getUserOpenId, int statusId) {
        List<Purchase> purchase_list = purchaseDao.getGetPurchase(getUserOpenId, statusId);
        return transform(purchase_list);
    }

    public String romovePurchase(int purchaseId) {
        int sql_back1 = purchaseItemDao.batchDeletePurchaseItems(purchaseId);
        int sql_back2 = purchaseDao.deletePurchase(purchaseId);
        if (sql_back1 == 0 || sql_back2 == 0) {
			return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
			return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    public String sendCanclePurchase(int purchaseId, boolean sendUserCancle) {
        int sql_back = purchaseDao.sendCanclePurchase(purchaseId, sendUserCancle);
        if (sql_back == 0) {
			return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
			return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    public String getCanclePurchase(int purchaseId) {
        int sql_back = purchaseDao.getCanclePurchase(purchaseId);
        if (sql_back == 0) {
			return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
			return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    public String getCompletePurchase(int purchaseId, boolean getUserComplete) {
        int sql_back = purchaseDao.getCompletePurchase(purchaseId, getUserComplete);
        if (sql_back == 0) {
			return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
			return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    public String sendCompletePurchase(int purchaseId) {
        int sql_back = purchaseDao.sendCompletePurchase(purchaseId);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    /**
     * ���������ת�������ǰ̨ʶ������
     */
    public String transform(List<Purchase> purchase_list) {
        List<Object> list = new ArrayList<>();
        Map<String, Object> item_map;
        for (Purchase purchase : purchase_list) {
            item_map = new HashMap<>();

            Address address = addressDao.findById(purchase.getAddressId());
            List<PurchaseItem> purchase_items = purchaseItemDao.findPurchaseItemsByPurchaseId(purchase.getId());
            item_map.put("id", purchase.getId());
            item_map.put("type", purchaseTypeDao.findById(purchase.getTypeId()));
            item_map.put("address", address);
            item_map.put("building", buildingDao.findById(address.getBuilding_id()));
            item_map.put("status", purchaseStatusDao.findById(purchase.getStatusId()));
            item_map.put("reward", purchase.getReward());
            item_map.put("sendUserInfo", userInfoDao.findByOpenId(purchase.getSendUserOpenId()));
            if (purchase.getGetUserOpenId() != null) {
                item_map.put("getUserInfo", userInfoDao.findByOpenId(purchase.getGetUserOpenId()));
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
