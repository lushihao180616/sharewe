package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.express.Express;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ExpressMapper {

    /**
     * 创建快递
     *
     * @param express
     * @return
     */
    int createExpress(Express express);

    /**
     * 更新快递
     *
     * @param express
     * @return
     */
    int updateExpress(Express express);

    /**
     * 接快递者接收快递
     *
     * @param buildingId
     * @param express_lastId
     * @param nowDate
     * @return
             */
    List<Express> filterExpress(@Param("buildingId") int buildingId, @Param("express_lastId") int express_lastId, @Param("nowDate") Date nowDate);

    /**
     * 获取一条快递
     *
     * @param id
     * @return
     */
    Express getOneExpress(@Param("buildingId") int id);

}
