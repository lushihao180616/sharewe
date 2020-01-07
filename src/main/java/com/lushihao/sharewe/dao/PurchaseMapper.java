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

    /**
     * 获取任务信息列表
     */
    List<Purchase> findPurchases(int num, int offset, Date nowDate);

    /**
     * 获取被用户
     */
    int getPurchase(Purchase purchase);

    /**
     * ��������
     */
    List<Purchase> filterPurchases(int num, int offset, Date nowDate, @Param("buildingId") int buildingId, @Param("typeId") int typeId);

    /**
     * ��ȡ��������
     *
     * @param statusId
     */
    List<Purchase> getSendPurchase(String sendUserOpenId, int statusId);

    /**
     * ��ȡ��������
     */
    List<Purchase> getGetPurchase(String getUserOpenId, int statusId);

    /**
     * ɾ������
     */
    int deletePurchase(int id);

    /**
     * ���������û�ȡ������
     */
    int sendCanclePurchase(int purchaseId, boolean sendUserCancle);

    /**
     * ���������û�ͬ��ȡ������
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
