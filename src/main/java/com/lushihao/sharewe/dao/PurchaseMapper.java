package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.purchase.Purchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PurchaseMapper {

    /**
     * 创建任务
     *
     * @param purchase
     * @return
     */
    int createPurchase(Purchase purchase);

    /**
     * 更新任务
     *
     * @param purchase
     * @return
     */
    int updatePurchase(Purchase purchase);

    /**
     * 接任务者接收任务
     *
     * @param buildingId
     * @param typeId
     * @param purchase_lastId
     * @param nowDate
     * @return
     */
    List<Purchase> filterPurchases(@Param("buildingId") int buildingId, @Param("typeId") int typeId, @Param("purchase_lastId") int purchase_lastId, @Param("nowDate") Date nowDate);

    /**
     * 接收任务者接收任务
     *
     * @param purchase
     * @return
     */
    int getPurchase(Purchase purchase);

    /**
     * 发送任务者发送任务集合
     *
     * @param statusId
     */
    List<Purchase> getSendPurchase(@Param("sendUserOpenId") String sendUserOpenId, @Param("statusId") int statusId, @Param("nowDate") Date nowDate);

    /**
     * 接收任务者接收任务集合
     *
     * @param getUserOpenId
     * @param statusId
     * @return
     */
    List<Purchase> getGetPurchase(@Param("getUserOpenId") String getUserOpenId, @Param("statusId") int statusId, @Param("nowDate") Date nowDate);

    /**
     * 获取一条任务
     *
     * @param id
     * @return
     */
    Purchase getOnePurchase(int id);

    /**
     * 删除任务
     *
     * @param id
     * @return
     */
    int deletePurchase(int id);

    /**
     * 发送任务者点击取消按钮
     *
     * @param purchaseId
     * @param sendUserCancle
     * @return
     */
    int sendCanclePurchase(@Param("purchaseId") int purchaseId, @Param("sendUserCancle") boolean sendUserCancle);

    /**
     * 接收任务者点击取消按钮
     *
     * @param purchaseId
     * @return
     */
    int getCanclePurchase(@Param("purchaseId") int purchaseId);

    /**
     * 接收任务者点击完成按钮
     *
     * @param purchaseId
     * @param sendUserCancle
     * @return
     */
    int getCompletePurchase(@Param("purchaseId") int purchaseId, @Param("sendUserCancle") boolean sendUserCancle);

    /**
     * 发送任务者点击完成按钮
     *
     * @param purchaseId
     * @return
     */
    int sendCompletePurchase(@Param("purchaseId") int purchaseId);

}
