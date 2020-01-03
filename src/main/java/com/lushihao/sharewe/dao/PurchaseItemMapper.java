package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.PurchaseItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseItemMapper {
	/**
	 * ��������������Ʒ
	 */
	int batchCreatePurchaseItems(List<PurchaseItem> purchaseItems);

	/**
	 * ����ɾ��������Ʒ
	 */
	int batchDeletePurchaseItems(int purchaseId);

	/**
	 * ͨ������������ȡ������Ʒ�б�
	 */
	List<PurchaseItem> findPurchaseItemsByPurchaseId(int purchaseId);

	int batchUpdatePurchaseItems(List<PurchaseItem> purchaseItems);
}
