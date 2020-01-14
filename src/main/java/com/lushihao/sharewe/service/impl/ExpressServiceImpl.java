package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.*;
import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.entity.express.ExpressItem;
import com.lushihao.sharewe.entity.purchase.Purchase;
import com.lushihao.sharewe.entity.purchase.PurchaseItem;
import com.lushihao.sharewe.service.ExpressService;
import com.lushihao.sharewe.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@EnableTransactionManagement
public class ExpressServiceImpl implements ExpressService {

    @Resource
    private ExpressMapper expressMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private ExpressItemMapper expressItemMapper;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 发送快递
     *
     * @param express
     * @return
     */
    @Override
    @Transactional
    public String sendExpress(Express express) {
        int sql_back;
        if (express.getId() == 0) {
            //创建快递
            sql_back = expressMapper.createExpress(express);
            if (sql_back > 0) {
                //地址使用数量更新
                addressMapper.updateAddressUsedCount(express.getAddressId(), 1);
                //消耗掉应消耗的捎点
                userInfoMapper.pointOut(express.getSendUserOpenId(), (int) (express.getReward() + express.getGuarantee()));
            }
        } else {//更新快递
            //先判断这个，快递是不是已经被别人接了
            Express nowExpress = expressMapper.getOneExpress(express.getId());
            if (nowExpress.getStatusId() == 2) {
                return LSHResponseUtils.getResponse(new LSHResponse("快递已经被接收了，请联系接任务人申请取消吧"));
            }
            //执行更新任务
            sql_back = expressMapper.updateExpress(express);
            //捎点多退少补
            int pointjs = (int) (express.getReward() + express.getGuarantee() - nowExpress.getReward() - nowExpress.getGuarantee());
            if (pointjs > 0) {
                //补充的捎点
                userInfoMapper.pointOut(express.getSendUserOpenId(), pointjs);
            } else if (pointjs < 0) {
                //退还的捎点
                userInfoMapper.pointIn(express.getSendUserOpenId(), -pointjs);
            }
        }
        //执行更新、插入成功
        if (sql_back != 0) {
            //快递单元赋值
            for (ExpressItem expressItem : express.getExpressItems()) {
                expressItem.setExpressId(express.getId());
            }
            //更新快递，先删除快递单元
            if (express.getId() != 0) {
                expressItemMapper.batchDeleteExpressItems(express.getId());
            }
            //创建快递单元
            int batch_sql_back = expressItemMapper.batchCreateExpressItems(express.getExpressItems());
            //执行成功
            if (batch_sql_back == express.getExpressItems().size()) {
                return userInfoService.findUserInfoByOpenId(express.getSendUserOpenId());
            }
        }
        return LSHResponseUtils.getResponse(new LSHResponse("调用失败，请稍后再试"));
    }

}
