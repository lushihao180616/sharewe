package com.lushihao.sharewe.dao.purchase;

import com.lushihao.sharewe.entity.purchase.PurchaseEvaluate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseEvaluateMapper {

    int sendPurchaseEvaluate(PurchaseEvaluate purchaseEvaluate);

}
