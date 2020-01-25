package com.lushihao.sharewe.service.express.impl;

import com.lushihao.myutils.collection.LSHMapUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.dao.express.ExpressItemMapper;
import com.lushihao.sharewe.dao.express.ExpressMapper;
import com.lushihao.sharewe.dao.express.ExpressTypeAndNumMapper;
import com.lushihao.sharewe.dao.userinfo.AddressMapper;
import com.lushihao.sharewe.dao.userinfo.BuildingMapper;
import com.lushihao.sharewe.dao.userinfo.UserInfoMapper;
import com.lushihao.sharewe.entity.express.*;
import com.lushihao.sharewe.entity.userinfo.Address;
import com.lushihao.sharewe.entity.yml.ProjectBasicInfo;
import com.lushihao.sharewe.enums.express.ExpressStatusEnum;
import com.lushihao.sharewe.enums.point.PointRecordTypeEnum;
import com.lushihao.sharewe.service.express.ExpressService;
import com.lushihao.sharewe.service.userinfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

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
    private BuildingMapper buildingMapper;
    @Resource
    private ExpressItemMapper expressItemMapper;
    @Resource
    private ExpressTypeAndNumMapper expressTypeAndNumMapper;
    @Autowired
    private AllExpressType allExpressType;
    @Autowired
    private ProjectBasicInfo projectBasicInfo;
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
    public LSHResponse sendExpress(Express express, int originalAddressId) {
        int sql_back = 0;
        if (express.getId() == 0) {
            //创建快递
            sql_back = expressMapper.createExpress(express);
            if (sql_back > 0) {
                //地址使用数量更新
                addressMapper.updateAddressUsedCount(express.getAddressId(), 1);
            }
        } else {//更新快递
            //先判断这个，快递是不是已经被别人接了
            Express nowExpress = expressMapper.getOneExpress(express.getId());
            if (nowExpress.getStatusId() == 2) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new LSHResponse("快递已经被接收了，请联系接快递人申请取消吧");
            }
            //地址有修改
            if (originalAddressId != express.getAddressId()) {
                addressMapper.updateAddressUsedCount(originalAddressId, 0);
                addressMapper.updateAddressUsedCount(express.getAddressId(), 1);
            }
            //执行更新快递
            sql_back = expressMapper.updateExpress(express);
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
                return new LSHResponse((Map<String, Object>) null);
            }
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return new LSHResponse("调用失败，请稍后再试");
    }

    /**
     * 接收快递者接收快递列表，（带分页，通过本页面最后一个快递id）
     *
     * @param buildingId
     * @param express_lastId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse getExpresses(int buildingId, int express_lastId) {
        //最晚接收几分钟马上要超期的快递
        Date lastGetDate = LSHDateUtils.dateAdd(new Date(), projectBasicInfo.getExpressAdvanceMinute(), LSHDateUtils.MINUTE);
        List<Express> express_list = expressMapper.filterExpress(buildingId, express_lastId, lastGetDate);

        return transform(express_list);
    }

    /**
     * 接收快递者接收快递
     *
     * @param express
     * @return
     */
    @Override
    @Transactional
    public LSHResponse getExpress(Express express) {
        int sql_back = expressMapper.getExpress(express);
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("快递已经被别人抢走了");
        } else {
            return new LSHResponse((Map<String, Object>) null);
        }
    }

    /**
     * 快递员发送需要支付的赏金
     *
     * @param express
     * @return
     */
    @Override
    @Transactional
    public LSHResponse sendExpressReward(Express express) {
        int sql_back = expressMapper.sendExpressReward(express);
        //更新快递类型，先删除快递类型单元
        expressTypeAndNumMapper.batchDeleteExpressTypeAndNums(express.getId());
        for (ExpressTypeAndNum expressTypeAndNum : express.getExpressTypeAndNums()) {
            expressTypeAndNum.setExpressId(express.getId());
        }
        //创建快递类型单元
        expressTypeAndNumMapper.batchCreateExpressTypeAndNums(express.getExpressTypeAndNums());
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("快递类型修改失败，请稍后再试");
        } else {
            return new LSHResponse((Map<String, Object>) null);
        }
    }

    /**
     * 发快递人支付赏金
     *
     * @param express
     * @return
     */
    @Override
    @Transactional
    public LSHResponse payExpressReward(Express express) {
        userInfoService.pointOut(express.getSendUserOpenId(), express.getReward(), PointRecordTypeEnum.TYPE_EXPRESS_SEND_PAY.getId());
        int sql_back = expressMapper.payExpressReward(express);
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("请求失败，请稍后再试");
        } else {
            return new LSHResponse((Map<String, Object>) null);
        }
    }

    /**
     * 删除快递
     *
     * @param expressId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse removeExpress(int expressId) {
        //要被删除的快递
        Express express = expressMapper.getOneExpress(expressId);
        if (express.getStatusId() == 2) {
            return new LSHResponse("快递已经被接收了，请联系接快递人申请取消吧");
        }
        //需要批量删除的快递单元
        expressItemMapper.batchDeleteExpressItems(expressId);
        //需要删除的快递
        int sql_back = expressMapper.deleteExpress(expressId);
        addressMapper.updateAddressUsedCount(express.getAddressId(), 0);
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("删除失败，请稍后再试");
        } else {
            return new LSHResponse((Map<String, Object>) null);
        }
    }

    /**
     * 发送快递者查看快递快递
     *
     * @param sendUserOpenId
     * @param statusId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse getSendExpress(String sendUserOpenId, int statusId) {
        //获取快递是否超时的时间点
        Date lastGetDate = LSHDateUtils.dateAdd(new Date(), projectBasicInfo.getExpressAdvanceMinute(), LSHDateUtils.MINUTE);
        List<Express> express_list = expressMapper.getSendExpress(sendUserOpenId, statusId, lastGetDate);
        return transform(express_list);
    }

    /**
     * 接收快递者接收快递集合
     *
     * @param getUserOpenId
     * @param statusId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse getGetExpress(String getUserOpenId, int statusId) {
        List<Express> express_list = expressMapper.getGetExpress(getUserOpenId, statusId, new Date());
        return transform(express_list);
    }

    /**
     * 发送快递者点击取消按钮
     *
     * @param expressId
     * @param sendUserCancle
     * @return
     */
    @Override
    @Transactional
    public LSHResponse sendCancleExpress(int expressId, boolean sendUserCancle) {
        int sql_back = expressMapper.sendCancleExpress(expressId, sendUserCancle);
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("取消失败，请稍后再试");
        } else {
            return new LSHResponse((Map<String, Object>) null);
        }
    }

    /**
     * 接收快递者点击取消按钮
     *
     * @param expressId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse getCancleExpress(int expressId, String getUserOpenId) {
        int sql_back = expressMapper.getCancleExpress(expressId);
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("取消失败，请稍后再试");
        } else {
            return userInfoService.findUserInfoByOpenId(getUserOpenId);
        }
    }

    /**
     * 接收快递者点击完成按钮
     *
     * @param expressId
     * @param getUserComplete
     * @return
     */
    @Override
    @Transactional
    public LSHResponse getCompleteExpress(int expressId, boolean getUserComplete) {
        int sql_back = expressMapper.getCompleteExpress(expressId, getUserComplete);
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("请求失败，请稍后再试");
        } else {
            return new LSHResponse((Map<String, Object>) null);
        }
    }

    /**
     * 发送快递者点击完成按钮
     *
     * @param expressId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse sendCompleteExpress(int expressId, int reward, String sendUserOpenId, String getUserOpenId, int addressId) {
        int sql_back = expressMapper.sendCompleteExpress(expressId);
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("请求失败，请稍后再试");
        } else {
            userInfoService.pointIn(getUserOpenId, reward, PointRecordTypeEnum.TYPE_EXPRESS_GET_COMPLETE.getId());
            addressMapper.updateAddressUsedCount(addressId, 0);
            return userInfoService.findUserInfoByOpenId(sendUserOpenId);
        }
    }

    /**
     * 转换
     *
     * @param express_list
     * @return
     */
    @Transactional
    public LSHResponse transform(List<Express> express_list) {
        List<Object> list = new ArrayList<>();
        Map<String, Object> item_map;
        for (Express express : express_list) {
            item_map = new HashMap<>();

            Address address = addressMapper.findById(express.getAddressId());
            List<ExpressItem> express_items = expressItemMapper.findExpressItemsByExpressId(express.getId());
            item_map.put("id", express.getId());
            item_map.put("address", address);
            item_map.put("building", buildingMapper.findById(address.getBuilding_id()));
            item_map.put("status", ExpressStatusEnum.getOne(express.getStatusId(), null));
            item_map.put("reward", express.getReward());
            item_map.put("sendUserInfo", userInfoMapper.findByOpenId(express.getSendUserOpenId()));
            if (express.getGetUserOpenId() != null) {
                item_map.put("getUserInfo", userInfoMapper.findByOpenId(express.getGetUserOpenId()));
            }
            item_map.put("deadTime", LSHDateUtils.date2String(express.getDeadTime(), LSHDateUtils.YYYY_MM_DD_HH_MM_SS1));
            item_map.put("sendTime", LSHDateUtils.date2String(express.getSendTime(), LSHDateUtils.YYYY_MM_DD_HH_MM_SS1));
            if (express.getGetTime() != null) {
                item_map.put("getTime", LSHDateUtils.date2String(express.getGetTime(), LSHDateUtils.YYYY_MM_DD_HH_MM_SS1));
            }
            item_map.put("expressItems", express_items);
            if (express.getReward() != 0) {
                List<ExpressTypeAndNum> express_typeAndNums = expressTypeAndNumMapper.findExpressTypeAndNumByExpressId(express.getId());
                List<Map<String, Object>> type_list = new ArrayList<>();
                for (ExpressTypeAndNum expressTypeAndNum : express_typeAndNums) {
                    Map<String, Object> type_map = LSHMapUtils.entityToMap(expressTypeAndNum);
                    type_map.put("type", allExpressType.getItem(new ExpressType(null, (String) type_map.get("typeCode"), null, null)).get(0));
                    type_list.add(type_map);
                }
                item_map.put("expressTypeAndNums", type_list);
            }
            item_map.put("sendUserCancle", express.isSendUserCancle());
            item_map.put("getUserComplete", express.isGetUserComplete());
            item_map.put("sendUserPay", express.isSendUserPay());
            list.add(item_map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("express_list", list);
        return new LSHResponse(map);
    }

}
