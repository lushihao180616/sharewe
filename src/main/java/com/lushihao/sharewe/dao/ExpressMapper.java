package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.entity.purchase.Purchase;
import org.apache.ibatis.annotations.Mapper;

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
     * 获取一条快递
     *
     * @param id
     * @return
     */
    Express getOneExpress(int id);

}
