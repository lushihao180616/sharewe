package com.lushihao.sharewe.dao.purchase;

import com.lushihao.sharewe.entity.purchase.PurchaseItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseItemMapper {

	/**
	 * 批量创建任务单元
	 */
	int batchCreatePurchaseItems(List<PurchaseItem> purchaseItems);

	/**
	 * 批量删除任务单元
	 */
	int batchDeletePurchaseItems(int purchaseId);

	/**
	 * ͨ通过任务获取任务单元列表
	 */
	List<PurchaseItem> findPurchaseItemsByPurchaseId(int purchaseId);

}
