package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PurchaseMapper {
    /**
     * 创建任务
     */
    int createPurchase(Purchase purchase);

    /**
     * 更新任务
     */
    int updatePurchase(Purchase purchase);

    List<Purchase> findPurchases(int num, int offset, Date nowDate);

    int getPurchase(Purchase purchase);

    /**
     * 过滤数据
     */
    List<Purchase> filterPurchases(int num, int offset, Date nowDate, @Param("buildingId") int buildingId, @Param("typeId") int typeId);

    /**
     * 发送任务者发送任务集合
     *
     * @param statusId
     */
    List<Purchase> getSendPurchase(String sendUserOpenId, int statusId);

    /**
     * 接收任务者接收任务集合
     */
    List<Purchase> getGetPurchase(String getUserOpenId, int statusId);

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
    int sendCanclePurchase(int purchaseId, boolean sendUserCancle);

    /**
     * 接收任务者点击取消按钮
     *
     * @param purchaseId
     * @return
     */
    int getCanclePurchase(int purchaseId);

    /**
     * 接收任务者点击完成按钮
     *
     * @param purchaseId
     * @param sendUserCancle
     * @return
     */
    int getCompletePurchase(int purchaseId, boolean sendUserCancle);

    /**
     * 发送任务者点击完成按钮
     *
     * @param purchaseId
     * @return
     */
    int sendCompletePurchase(int purchaseId);
}
