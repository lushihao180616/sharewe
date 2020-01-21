package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.confession.ConfessionWallItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConfessionWallItemMapper {

    /**
     * 批量创建告白墙单元信息
     *
     * @param needInfoList
     */
    void batchCreateConfessionWallItem(List<ConfessionWallItem> needInfoList);

    /**
     * 批量删除告白墙单元
     */
    int batchDeleteConfessionWallItem(@Param("wallId") int wallId);

}
