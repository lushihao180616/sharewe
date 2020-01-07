package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.PurchaseItem;
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
	 * ͨ������������ȡ������Ʒ�б�
	 */
	List<PurchaseItem> findPurchaseItemsByPurchaseId(int purchaseId);

	int batchUpdatePurchaseItems(List<PurchaseItem> purchaseItems);
}
