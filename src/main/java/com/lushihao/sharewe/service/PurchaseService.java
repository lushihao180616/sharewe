package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.purchase.Purchase;

public interface PurchaseService {

	String sendPurchase(Purchase purchase);

	String getPurchases(int num, int page);

	String getPurchase(Purchase purchase);

	String filterPurchases(int num, int page, int buildingId, int typeId);

	String getSendPurchase(String sendUserOpenId, int statusId);

	String getGetPurchase(String getUserOpenId, int statusId);

	String romovePurchase(int purchaseId);

	String sendCanclePurchase(int purchaseId, boolean sendUserCancle);

	String getCanclePurchase(int purchaseId);

	String getCompletePurchase(int purchaseId, boolean getUserComplete);
	
	String sendCompletePurchase(int purchaseId);

}
