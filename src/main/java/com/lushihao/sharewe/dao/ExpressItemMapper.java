package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.express.ExpressItem;

import java.util.List;

public interface ExpressItemMapper {

    /**
     * 批量创建快递单元
     */
    int batchCreateExpressItems(List<ExpressItem> expressItems);

    /**
     * 批量删除快递单元
     */
    int batchDeleteExpressItems(int expressId);

}
