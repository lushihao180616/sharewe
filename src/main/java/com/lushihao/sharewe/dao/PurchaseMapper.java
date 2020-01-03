package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PurchaseMapper {

    /**
     * ��������
     */
    int createPurchase(Purchase purchase);

    /**
     * ��������
     */
    int updatePurchase(Purchase purchase);

    /**
     * ��ȡ��������
     */
    List<Purchase> findPurchases(int num, int offset, Date nowDate);

    /**
     * ������
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
     * ���������û��������
     */
    int getCompletePurchase(int purchaseId, boolean sendUserCancle);

    /**
     * ���������û�ͬ���������
     */
    int sendCompletePurchase(int purchaseId);
}
