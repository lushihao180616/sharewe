package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.dao.*;
import com.lushihao.sharewe.entity.yml.AllPurchaseType;
import com.lushihao.sharewe.entity.purchase.Purchase;
import com.lushihao.sharewe.entity.purchase.PurchaseItem;
import com.lushihao.sharewe.entity.yml.PurchaseType;
import com.lushihao.sharewe.entity.userinfo.Address;
import com.lushihao.sharewe.enums.PurchaseStatusEnum;
import com.lushihao.sharewe.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PurchaseItemMapper purchaseItemMapper;
    @Autowired
    private AllPurchaseType allPurchaseType;

    /**
     * 发送任务这发送任务（或更新任务）
     *
     * @param purchase
     * @return
     */
    @Override
    @Transactional
    public String sendPurchase(Purchase purchase) {
        int sql_back;
        if (purchase.getId() == 0) {//创建任务
            sql_back = purchaseMapper.createPurchase(purchase);
            if (sql_back > 0) {//地址使用数量更新
                addressMapper.updateAddressUsedCount(purchase.getAddressId());
                userInfoMapper.pointOut(purchase.getSendUserOpenId(), (int) (purchase.getReward() + purchase.getGuarantee()));
            }
        } else {//更新任务
            Purchase nowPurchase = purchaseMapper.getOnePurchase(purchase.getId());
            if (nowPurchase.getStatusId() != 1) {
                return LSHResponseUtils.getResponse(new LSHResponse("任务已经被接收了，请联系接任务人申请取消吧"));
            }
            int pointjs = (int) (purchase.getReward() + purchase.getGuarantee() - nowPurchase.getReward() - nowPurchase.getGuarantee());
            if (pointjs > 0) {
                userInfoMapper.pointOut(purchase.getSendUserOpenId(), pointjs);
            } else if (pointjs < 0) {
                userInfoMapper.pointIn(purchase.getSendUserOpenId(), -pointjs);
            }
            sql_back = purchaseMapper.updatePurchase(purchase);
        }
        if (sql_back != 0) {//说明执行成功
            for (PurchaseItem purchaseItem : purchase.getPurchaseItems()) {
                purchaseItem.setPurchaseId(purchase.getId());
            }
            if (purchase.getId() != 0) {//更新任务
                purchaseItemMapper.batchDeletePurchaseItems(purchase.getId());
            }
            int batch_sql_back = purchaseItemMapper.batchCreatePurchaseItems(purchase.getPurchaseItems());
            if (batch_sql_back == purchase.getPurchaseItems().size()) {//执行成功
                return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
            }
        }
        return LSHResponseUtils.getResponse(new LSHResponse("调用失败，请稍后再试"));
    }

    /**
     * 接收任务者接收任务列表
     *
     * @return
     */
    @Override
    @Transactional
    public String getPurchases(int buildingId, int typeId, int purchase_lastId) {
        Date lastGetDate = LSHDateUtils.dateAdd(new Date(), 5, LSHDateUtils.MINUTE);
        List<Purchase> purchase_list = purchaseMapper.findPurchases(buildingId, typeId, purchase_lastId, lastGetDate);

        return transform(purchase_list);
    }

    /**
     * 接收任务者接收任务
     *
     * @param purchase
     * @return
     */
    @Override
    @Transactional
    public String getPurchase(Purchase purchase) {
        int sql_back = purchaseMapper.getPurchase(purchase);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("任务已经被别人抢走了"));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    /**
     * 发送任务者查看发送任务
     *
     * @param sendUserOpenId
     * @param statusId
     * @return
     */
    @Override
    @Transactional
    public String getSendPurchase(String sendUserOpenId, int statusId) {
        List<Purchase> purchase_list = purchaseMapper.getSendPurchase(sendUserOpenId, statusId);
        return transform(purchase_list);
    }

    /**
     * 接收任务者接收任务集合
     *
     * @param getUserOpenId
     * @param statusId
     * @return
     */
    @Override
    @Transactional
    public String getGetPurchase(String getUserOpenId, int statusId) {
        List<Purchase> purchase_list = purchaseMapper.getGetPurchase(getUserOpenId, statusId);
        return transform(purchase_list);
    }

    /**
     * 删除任务
     *
     * @param purchaseId
     * @return
     */
    @Override
    @Transactional
    public String romovePurchase(int purchaseId) {
        Purchase purchase = purchaseMapper.getOnePurchase(purchaseId);
        if (purchase.getStatusId() != 1) {
            return LSHResponseUtils.getResponse(new LSHResponse("任务已经被接收了，请联系接任务人申请取消吧"));
        }
        int sql_back1 = purchaseItemMapper.batchDeletePurchaseItems(purchaseId);
        int sql_back2 = 0;
        if (sql_back1 > 0) {
            sql_back2 = purchaseMapper.deletePurchase(purchaseId);
            userInfoMapper.pointIn(purchase.getSendUserOpenId(), (int) (purchase.getReward() + purchase.getGuarantee()));
        }
        if (sql_back1 == 0 || sql_back2 == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("删除失败，请稍后再试"));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    /**
     * 发送任务者点击取消按钮
     *
     * @param purchaseId
     * @param sendUserCancle
     * @return
     */
    @Override
    @Transactional
    public String sendCanclePurchase(int purchaseId, boolean sendUserCancle) {
        int sql_back = purchaseMapper.sendCanclePurchase(purchaseId, sendUserCancle);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("取消失败，请稍后再试"));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    /**
     * 接收任务者点击取消按钮
     *
     * @param purchaseId
     * @return
     */
    @Override
    @Transactional
    public String getCanclePurchase(int purchaseId) {
        int sql_back = purchaseMapper.getCanclePurchase(purchaseId);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("取消失败，请稍后再试"));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    /**
     * 接收任务者点击完成按钮
     *
     * @param purchaseId
     * @param getUserComplete
     * @return
     */
    @Override
    @Transactional
    public String getCompletePurchase(int purchaseId, boolean getUserComplete) {
        int sql_back = purchaseMapper.getCompletePurchase(purchaseId, getUserComplete);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("完成失败，请稍后再试"));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    /**
     * 发送任务者点击完成按钮
     *
     * @param purchaseId
     * @return
     */
    @Override
    @Transactional
    public String sendCompletePurchase(int purchaseId) {
        int sql_back = purchaseMapper.sendCompletePurchase(purchaseId);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("完成失败，请稍后再试"));
        } else {
            return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
        }
    }

    @Transactional
    public String transform(List<Purchase> purchase_list) {
        List<Object> list = new ArrayList<>();
        Map<String, Object> item_map;
        for (Purchase purchase : purchase_list) {
            item_map = new HashMap<>();

            Address address = addressMapper.findById(purchase.getAddressId());
            List<PurchaseItem> purchase_items = purchaseItemMapper.findPurchaseItemsByPurchaseId(purchase.getId());
            item_map.put("id", purchase.getId());
            item_map.put("type", allPurchaseType.getItem(new PurchaseType(purchase.getTypeId(), null, null)).get(0));
            item_map.put("address", address);
            item_map.put("building", buildingMapper.findById(address.getBuilding_id()));
            item_map.put("status", PurchaseStatusEnum.getOne(purchase.getStatusId(), null));
            item_map.put("reward", purchase.getReward());
            item_map.put("guarantee", purchase.getGuarantee());
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
        Map<String, Object> map = new HashMap<>();
        map.put("purchase_list", list);
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}
