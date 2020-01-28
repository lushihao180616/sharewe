package com.lushihao.sharewe.service.userinfo.impl;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.collection.LSHMapUtils;
import com.lushihao.myutils.http.LSHHttpUtils;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.userinfo.AddressMapper;
import com.lushihao.sharewe.dao.userinfo.PointRecordMapper;
import com.lushihao.sharewe.dao.userinfo.UserInfoMapper;
import com.lushihao.sharewe.dao.userinfo.UserProfessionTypeRecordMapper;
import com.lushihao.sharewe.entity.userinfo.*;
import com.lushihao.sharewe.enums.point.PointRecordTypeEnum;
import com.lushihao.sharewe.service.userinfo.UserInfoService;
import com.lushihao.sharewe.util.LSHUserInfoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableTransactionManagement
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private PointRecordMapper pointRecordMapper;
    @Resource
    private AllUserProfessionType allUserProfessionType;
    @Resource
    private UserProfessionTypeRecordMapper userProfessionTypeRecordMapper;

    /**
     * 处理获取用户信息方法
     *
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     */
    @Override
    @Transactional
    public LSHResponse handleGetUserInfo(String code, String encryptedData, String iv) {
        // 微信小程序appid和秘钥，用来获取或解析用户信息
        final String APPID = "wx15c0ebc110fd5eb5";
        final String SECRET = "5a1a14595cad33adb86caa3af477653a";

        // 第一次请求，通过code获取session_key和openid
        if (code != null) {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET
                    + "&js_code=" + code + "&grant_type=authorization_code";
            String wxResponse = LSHHttpUtils.request(url, 10000, 10000, LSHHttpUtils.HttpMethod.POST);
            JSONObject wxResponseJson = LSHJsonUtils.string2JsonObj(wxResponse);
            String session_key = wxResponseJson.getString("session_key");
            String openId = wxResponseJson.getString("openid");

            // 第二次请求，通过session_key解析出用户信息
            if (session_key != null && encryptedData != null && iv != null) {

                // 使用session_key获取用户加密的信息
                JSONObject userInfoJson = LSHUserInfoUtils.getUserInfo(encryptedData, session_key, iv);

                UserInfo userInfo = new UserInfo();
                userInfo.setOpenId(userInfoJson.getString("openId"));
                userInfo.setNickName(userInfoJson.getString("nickName"));
                userInfo.setGender(userInfoJson.getString("gender"));
                userInfo.setAvatarUrl(userInfoJson.getString("avatarUrl"));

                int sql_back = userInfoMapper.createUserInfo(userInfo);
                List<UserProfessionType> type_list = allUserProfessionType.getTypeList();
                List<UserProfessionTypeRecord> record_list = new ArrayList<>();
                for (UserProfessionType userProfessionType : type_list) {
                    UserProfessionTypeRecord record = new UserProfessionTypeRecord();
                    record.setIfOpen(false);
                    record.setOpenId(userInfo.getOpenId());
                    record.setTypeCode(userProfessionType.getCode());
                    record_list.add(record);
                }
                userProfessionTypeRecordMapper.batchCreateRecord(record_list);
                if (sql_back > 0) {
                    return findByOpenId(openId);
                }
            }
        }
        return new LSHResponse("登录失败，请稍后再试");
    }

    /**
     * 更新数据
     *
     * @param userInfo      用户信息
     * @param deleteAddress 是否删除地址
     * @return
     */
    @Override
    @Transactional
    public LSHResponse updateUserInfo(UserInfo userInfo, boolean deleteAddress) {
        int sql_back = userInfoMapper.updateUserInfo(userInfo);
        if (deleteAddress) {
            int flag = addressMapper.findByOpenId(userInfo.getOpenId()).size();
            if (flag != addressMapper.deleteAllAddress(userInfo.getOpenId())) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("更新失败，请稍后再试");
        } else {
            return findByOpenId(userInfo.getOpenId());
        }
    }

    /**
     * 通过OpenId获取用户信息
     *
     * @param openId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse findByOpenId(String openId) {
        Map<String, Object> map = new HashMap<>();

        // 获取基本信息
        AllUserInfo allUserInfo = userInfoMapper.findByOpenId(openId);

        map.put("userinfo", allUserInfo.getUserinfo());
        map.put("province_list", allUserInfo.getProvinceList());
        map.put("school_list", allUserInfo.getSchoolList());
        map.put("building_list", allUserInfo.getBuildingList());
        map.put("dormitory_list", allUserInfo.getDormitoryList());
        map.put("address_list", allUserInfo.getAddressList());
        return new LSHResponse(map);
    }

    /**
     * 添加捎点
     *
     * @param openId
     * @param needPoint
     * @return
     */
    @Override
    @Transactional
    public LSHResponse pointIn(String openId, int needPoint, int recordSourceType) {
        int sql_back = userInfoMapper.pointIn(openId, needPoint);
        pointRecordMapper.createPointRecord(new PointRecord(openId, recordSourceType, needPoint, 1));
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("充值失败，请稍后再试");
        } else {
            return findUserInfoByOpenId(openId);
        }
    }

    /**
     * 提现捎点
     *
     * @param openId
     * @param needPoint
     * @return
     */
    @Override
    @Transactional
    public LSHResponse pointOut(String openId, int needPoint, int recordSourceType) {
        int sql_back = userInfoMapper.pointOut(openId, needPoint);
        pointRecordMapper.createPointRecord(new PointRecord(openId, recordSourceType, needPoint, 2));
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("提现失败，请稍后再试");
        } else {
            return findUserInfoByOpenId(openId);
        }
    }

    /**
     * 通过OpenId获取用户信息()只有用户信息
     *
     * @param openId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse findUserInfoByOpenId(String openId) {
        Map<String, Object> map = new HashMap<>();

        // 获取基本信息
        AllUserInfo allUserInfo = userInfoMapper.findByOpenId(openId);

        map.put("userinfo", allUserInfo.getUserinfo());
        return new LSHResponse(map);
    }

    /**
     * 获得所有职业
     *
     * @param openId
     * @return
     */
    @Override
    @Transactional
    public LSHResponse getAllProfession(String openId) {
        Map<String, Object> map = new HashMap<>();
        // 共有两步：1.同步现有工作和原有工作，对比删除原有现在没有的工作，对比加入现有原来没有的工作
        // 获取基本信息
        List<UserProfessionTypeRecord> nowUserTypeList = userProfessionTypeRecordMapper.getAllProfession(openId);
        // 判断是否有数据
        List<UserProfessionType> userTypeList = allUserProfessionType.getTypeList();
        // 当前用户的类型
        List<String> nowUserTypeCode = new ArrayList<>();
        // 所有的类型
        List<String> userTypeCode = new ArrayList<>();
        for (UserProfessionTypeRecord record : nowUserTypeList) {
            nowUserTypeCode.add(record.getTypeCode());
        }
        for (UserProfessionType type : userTypeList) {
            userTypeCode.add(type.getCode());
        }

        List<UserProfessionTypeRecord> needCreateList = new ArrayList<>();
        List<UserProfessionTypeRecord> needDeleteList = new ArrayList<>();
        for (int i = 0; i < nowUserTypeCode.size(); i++) {
            String nowUserType = nowUserTypeCode.get(i);
            if (!userTypeCode.contains(nowUserType)) {
                needDeleteList.add(nowUserTypeList.get(i));
            }
        }
        for (int i = 0; i < userTypeCode.size(); i++) {
            String userType = userTypeCode.get(i);
            if (!nowUserTypeCode.contains(userType)) {
                UserProfessionTypeRecord userProfessionTypeRecord = new UserProfessionTypeRecord();
                userProfessionTypeRecord.setTypeCode(userType);
                userProfessionTypeRecord.setOpenId(openId);
                userProfessionTypeRecord.setIfOpen(false);
                needCreateList.add(userProfessionTypeRecord);
            }
        }
        if (needCreateList.size() > 0) {
            userProfessionTypeRecordMapper.batchCreateRecord(needCreateList);
        }
        if (needDeleteList.size() > 0) {
            userProfessionTypeRecordMapper.batchDeleteRecord(openId, needDeleteList);
        }

        List<UserProfessionTypeRecord> record_list = userProfessionTypeRecordMapper.getAllProfession(openId);
        List<Map<String, Object>> return_list = new ArrayList<>();
        for (UserProfessionTypeRecord backRecord : record_list) {
            Map<String, Object> returnRecord = LSHMapUtils.entityToMap(backRecord);
            String typeCode = (String) returnRecord.get("typeCode");
            returnRecord.remove("typeCode");
            returnRecord.put("type", allUserProfessionType.getItem(new UserProfessionType(null, typeCode, null)).get(0));
            return_list.add(returnRecord);
        }

        map.put("record_list", return_list);
        return new LSHResponse(map);
    }

    /**
     * 修改类型是否开启
     *
     * @param userProfessionTypeRecord
     * @return
     */
    @Override
    @Transactional
    public LSHResponse changeTypeOpen(UserProfessionTypeRecord userProfessionTypeRecord, int point) {
        int sql_back = userProfessionTypeRecordMapper.updateUserProfessionTypeRecord(userProfessionTypeRecord);
        if (point != 0) {
            if (userProfessionTypeRecord.isIfOpen()) {// 开启此职业
                pointOut(userProfessionTypeRecord.getOpenId(), point, PointRecordTypeEnum.TYPE_PROFESSIONCHANGE_EXPRESS_OPEN.getId());
            } else {
                pointIn(userProfessionTypeRecord.getOpenId(), point, PointRecordTypeEnum.TYPE_PROFESSIONCHANGE_EXPRESS_CLOSE.getId());
            }
        }
        if (sql_back == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new LSHResponse("提现失败，请稍后再试");
        } else {
            return getAllProfession(userProfessionTypeRecord.getOpenId());
        }
    }

}
