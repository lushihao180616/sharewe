package com.lushihao.sharewe.dao.express;

import com.lushihao.sharewe.entity.express.ExpressItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpressItemMapper {

    /**
     * 批量创建快递单元
     */
    int batchCreateExpressItems(List<ExpressItem> expressItems);

    /**
     * 批量删除快递单元
     */
    int batchDeleteExpressItems(@Param("expressId") int expressId);

    /**
     * ͨ通过快递获取快递单元列表
     */
    List<ExpressItem> findExpressItemsByExpressId(@Param("expressId") int expressId);

}
