package com.lushihao.sharewe.dao.confession;

import com.lushihao.sharewe.entity.confession.ConfessionWall;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfessionWallMapper {

    /**
     * 创建告白墙信息
     *
     * @param confessionWall
     * @return
     */
    int createConfessionWall(ConfessionWall confessionWall);

}
