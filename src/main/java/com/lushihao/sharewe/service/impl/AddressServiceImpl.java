package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.LSHResponseUtils;
import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.AddressMapper;
import com.lushihao.sharewe.dao.BuildingMapper;
import com.lushihao.sharewe.entity.Address;
import com.lushihao.sharewe.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableTransactionManagement
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    @Transactional
    public String createAddress(Address address) {
        Map<String, Object> map = new HashMap<>();

        int sql_back = addressMapper.createAddress(address);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("创建失败"));
        } else {
            map.put("address_list", findByOpenId(address.getOpenId()));
            return LSHResponseUtils.getResponse(new LSHResponse(map));
        }
    }

    @Override
    @Transactional
    public String updateAddress(Address address) {
        Map<String, Object> map = new HashMap<>();

        int sql_back = addressMapper.updateAddress(address);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("更新失败"));
        } else {
            map.put("address_list", findByOpenId(address.getOpenId()));
            return LSHResponseUtils.getResponse(new LSHResponse(map));
        }
    }

    @Override
    @Transactional
    public String deleteAddress(Address address) {
        Map<String, Object> map = new HashMap<>();

        int sql_back = addressMapper.deleteAddress(address);
        if (sql_back == 0) {
            return LSHResponseUtils.getResponse(new LSHResponse("删除失败"));
        } else {
            map.put("address_list", findByOpenId(address.getOpenId()));
            return LSHResponseUtils.getResponse(new LSHResponse(map));
        }
    }

    @Transactional
    List<Map<String, Object>> findByOpenId(String openId) {
        List<Map<String, Object>> address_list = addressMapper.findByOpenId(openId);
        return address_list;
    }
}
