package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.myutils.time.LSHDateUtils;
import com.lushihao.sharewe.dao.*;
import com.lushihao.sharewe.entity.express.AllExpressType;
import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.entity.express.ExpressItem;
import com.lushihao.sharewe.entity.purchase.Purchase;
import com.lushihao.sharewe.entity.userinfo.Address;
import com.lushihao.sharewe.entity.yml.ProjectBasicInfo;
import com.lushihao.sharewe.enums.express.ExpressStatusEnum;
import com.lushihao.sharewe.enums.point.PointRecordTypeEnum;
import com.lushihao.sharewe.service.ExpressService;
import com.lushihao.sharewe.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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
    public String sendExpress(Express express) {
        int sql_back = 0;
        if (express.getId() == 0) {
            //创建快递
            sql_back = expressMapper.createExpress(express);
            if (sql_back > 0) {
                //地址使用数量更新
                addressMapper.updateAddressUsedCount(express.getAddressId(), 1);
            }
        }
        //执行更新、插入成功
        if (sql_back != 0) {
            //快递单元赋值
            for (ExpressItem expressItem : express.getExpressItems()) {
                expressItem.setExpressId(express.getId());
            }
            //创建快递单元
            int batch_sql_back = expressItemMapper.batchCreateExpressItems(express.getExpressItems());
            //执行成功
            if (batch_sql_back == express.getExpressItems().size()) {
                return LSHResponseUtils.getResponse(new LSHResponse((Map<String, Object>) null));
            }
        }
        return LSHResponseUtils.getResponse(new LSHResponse("调用失败，请稍后再试"));
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
    public String getExpresses(int buildingId, int express_lastId) {
        //最晚接收几分钟马上要超期的快递
        Date lastGetDate = LSHDateUtils.dateAdd(new Date(), projectBasicInfo.getExpressAdvanceMinute(), LSHDateUtils.MINUTE);
        List<Express> express_list = expressMapper.filterExpress(buildingId, express_lastId, lastGetDate);

        return transform(express_list);
    }

    /**
     * 接收任务者接收任务
     *
     * @param express
     * @return
     */
    @Override
    @Transactional
    public String getExpress(Express express) {
        int sql_back = expressMapper.getExpress(express);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("快递已经被别人抢走了"));
        } else {
            return userInfoService.findUserInfoByOpenId(express.getGetUserOpenId());
        }
    }

    /**
     * 转换
     *
     * @param express_list
     * @return
     */
    @Transactional
    public String transform(List<Express> express_list) {
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
            list.add(item_map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("express_list", list);
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}
