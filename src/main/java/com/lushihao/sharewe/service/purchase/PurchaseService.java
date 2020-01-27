package com.lushihao.sharewe.service.purchase;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.entity.purchase.Purchase;
import com.lushihao.sharewe.entity.purchase.PurchaseEvaluate;

public interface PurchaseService {

    LSHResponse sendPurchase(Purchase purchase, int originalAddressId);

    LSHResponse getPurchases(int buildingId, int typeId, int purchase_lastId);

    LSHResponse getPurchase(Purchase purchase);

    LSHResponse removePurchase(int purchaseId);

    LSHResponse getSendPurchase(String sendUserOpenId, int statusId);

    LSHResponse getGetPurchase(String getUserOpenId, int statusId);

    LSHResponse sendCanclePurchase(int purchaseId, boolean sendUserCancle);

    LSHResponse getCanclePurchase(int purchaseId, int guarantee, String getUserOpenId);

    LSHResponse getCompletePurchase(int purchaseId, boolean getUserComplete);

    LSHResponse sendCompletePurchase(int purchaseId, int guarantee, int reward, String sendUserOpenId, String getUserOpenId, int addressId);

    LSHResponse sendPurchaseEvaluate(PurchaseEvaluate purchaseEvaluate, int purchaseId);
}
