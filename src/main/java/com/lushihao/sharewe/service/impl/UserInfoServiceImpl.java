package com.lushihao.sharewe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lushihao.myutils.http.LSHHttpUtils;
import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.AddressMapper;
import com.lushihao.sharewe.dao.UserInfoMapper;
import com.lushihao.sharewe.entity.AllUserInfo;
import com.lushihao.sharewe.entity.UserInfo;
import com.lushihao.sharewe.service.UserInfoService;
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

    /**
     * 处理获取用户信息方法
     */
    @Override
    @Transactional
    public String handleGetUserInfo(String code, String encryptedData, String iv) {
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
        return LSHResponseUtils.getResponse(new LSHResponse((String) null));
    }

    /**
     * 更新数据
     */
    @Override
    @Transactional
    public String updateUserInfo(UserInfo userInfo, boolean deleteAddress) {
        int sql_back = userInfoMapper.updateUserInfo(userInfo);
        if (deleteAddress) {
            addressMapper.deleteAllAddress(userInfo.getOpenId());
        }
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse((String) null));
        } else {
            return findByOpenId(userInfo.getOpenId());
        }
    }

    /**
     * 通过OpenId获取用户信息
     */
    @Override
    @Transactional
    public String findByOpenId(String openId) {
        Map<String, Object> map = new HashMap<>();

        // 获取基本信息
        AllUserInfo allUserInfo = userInfoMapper.findByOpenId(openId);

        map.put("userinfo", allUserInfo.getUserinfo());
        map.put("province_list", allUserInfo.getProvinceList());
        map.put("school_list", allUserInfo.getSchoolList());
        map.put("building_list", allUserInfo.getBuildingList());
        map.put("dormitory_list", allUserInfo.getDormitoryList());
        map.put("address_list", allUserInfo.getAddressList());
        return LSHResponseUtils.getResponse(new LSHResponse(map));
    }

}
