package com.lushihao.sharewe.service.userinfo.impl;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.http.LSHHttpUtils;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.userinfo.AddressMapper;
import com.lushihao.sharewe.dao.userinfo.PointRecordMapper;
import com.lushihao.sharewe.dao.userinfo.UserInfoMapper;
import com.lushihao.sharewe.entity.userinfo.AllUserInfo;
import com.lushihao.sharewe.entity.userinfo.PointRecord;
import com.lushihao.sharewe.entity.userinfo.UserInfo;
import com.lushihao.sharewe.service.userinfo.PointRecordService;
import com.lushihao.sharewe.service.userinfo.UserInfoService;
import com.lushihao.sharewe.util.LSHUserInfoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@EnableTransactionManagement
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private PointRecordService pointRecordService;
    @Resource
    private PointRecordMapper pointRecordMapper;

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
                try {
                    throw new RuntimeException("异常了");
                } catch (Exception e) {

                } finally {
                    return new LSHResponse("有地址正在被使用，无法删除");
                }
            }
        }
        if (sql_back == 0) {
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

}
