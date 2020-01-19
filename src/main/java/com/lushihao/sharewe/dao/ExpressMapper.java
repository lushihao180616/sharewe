package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.entity.purchase.Purchase;
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
     * 接收任务者接收任务
     *
     * @param express
     * @return
     */
    int getExpress(Express express);

    /**
     * 发送快递者发送快递集合
     *
     * @param statusId
     */
    List<Express> getSendExpress(@Param("sendUserOpenId") String sendUserOpenId, @Param("statusId") int statusId, @Param("nowDate") Date nowDate);

    /**
     * 接收快递者接收快递集合
     *
     * @param getUserOpenId
     * @param statusId
     * @return
     */
    List<Express> getGetExpress(@Param("getUserOpenId") String getUserOpenId, @Param("statusId") int statusId, @Param("nowDate") Date nowDate);

    /**
     * 获取一条快递
     *
     * @param id
     * @return
     */
    Express getOneExpress(int id);

    /**
     * 删除快递
     *
     * @param id
     * @return
     */
    int deleteExpress(int id);

    /**
     * 接收快递者点击完成按钮
     *
     * @param expressId
     * @param getUserComplete
     * @return
     */
    int getCompleteExpress(@Param("expressId") int expressId, @Param("getUserComplete") boolean getUserComplete);

    /**
     * 发送快递者点击完成按钮
     *
     * @param expressId
     * @return
     */
    int sendCompleteExpress(@Param("expressId") int expressId);

}
