package com.lushihao.sharewe.service.impl;

import com.lushihao.lshutils.http.LSHHttpUtils;
import com.lushihao.lshutils.string.LSHStringUtils;
import com.lushihao.sharewe.dao.BuildingMapper;
import com.lushihao.sharewe.dao.ProvinceMapper;
import com.lushihao.sharewe.dao.SchoolMapper;
import com.lushihao.sharewe.dao.UserInfoMapper;
import com.lushihao.sharewe.entity.AllUserInfo;
import com.lushihao.sharewe.entity.UserInfo;
import com.lushihao.sharewe.service.AddressService;
import com.lushihao.sharewe.service.UserInfoService;
import com.lushihao.sharewe.util.LSHResponseUtils;
import com.lushihao.sharewe.util.LSHUserInfoUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private ProvinceMapper provinceMapper;
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private BuildingMapper buildingMapper;
    @Resource
    private AddressService addressService;

    /**
     * 处理获取用户信息方法
     */
    public String handleGetUserInfo(String code, String encryptedData, String iv) {
        // 微信小程序appid和秘钥，用来获取或解析用户信息
        final String APPID = "wx15c0ebc110fd5eb5";
        final String SECRET = "15d1c4700159e5c147fb1d27ed49d880";

        // 第一次请求，通过code获取session_key和openid
        if (code != null) {
//            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET
//                    + "&js_code=" + code + "&grant_type=authorization_code";
//            String wxResponse = UrlUtil.getURLContent(url);
            String url = "https://api.weixin.qq.com/sns/jscode2session";
            Map<String, String> map = new HashMap<>();
            map.put("appid", APPID);
            map.put("secret", SECRET);
            map.put("js_code", code);
            map.put("grant_type", "authorization_code");
            String wxResponse = LSHHttpUtils.post(url, map, 60, 60, LSHStringUtils.UTF8);
            JSONObject wxResponseJson = JSONObject.fromObject(wxResponse);
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
        return LSHResponseUtils.responseParam(false, null);
    }

    /**
     * 通过OpenId获取用户信息
     */
    @SuppressWarnings("unused")
    public String findByOpenId(String openId) {
        Map<String, Object> map = new HashMap<>();

        // 获取基本信息
        AllUserInfo allUserInfo = userInfoMapper.findByOpenId(openId);

        boolean flag;
        if (allUserInfo != null) {
            flag = true;
        } else {
            flag = false;
        }
        map.put("userinfo", allUserInfo.getUserinfo());
        map.put("province_list", allUserInfo.getProvinceList());
        map.put("school_list", allUserInfo.getSchoolList());
        map.put("building_list", allUserInfo.getBuildingList());
        map.put("dormitory_list", allUserInfo.getDormitoryList());
        map.put("address_list", allUserInfo.getAddressList());
        return LSHResponseUtils.responseParam(flag, map);
    }

    /**
     * 更新数据
     */
    public String updateUserInfo(UserInfo userInfo) {
        int sql_back = userInfoMapper.updateUserInfo(userInfo);
        if (sql_back == 0) {
            return LSHResponseUtils.responseParam(false, null);
        } else {
            return findByOpenId(userInfo.getOpenId());
        }
    }

}
