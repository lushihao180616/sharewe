package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.purchase.Purchase;

public interface PurchaseService {

    String sendPurchase(Purchase purchase);

    String getPurchases(int buildingId, int typeId, int purchase_lastId);

    String getPurchase(Purchase purchase);

    String romovePurchase(int purchaseId);

    String getSendPurchase(String sendUserOpenId, int statusId);

    String getGetPurchase(String getUserOpenId, int statusId);

    String sendCanclePurchase(int purchaseId, boolean sendUserCancle);

    String getCanclePurchase(int purchaseId, int guarantee, String getUserOpenId);

    String getCompletePurchase(int purchaseId, boolean getUserComplete);

    String sendCompletePurchase(int purchaseId, int guarantee, int reward, String sendUserOpenId, String getUserOpenId, int addressId);

}
