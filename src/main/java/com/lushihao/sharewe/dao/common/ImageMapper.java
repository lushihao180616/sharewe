package com.lushihao.sharewe.dao.common;

import com.lushihao.sharewe.entity.common.Image;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    int addImage(Image image);

}
