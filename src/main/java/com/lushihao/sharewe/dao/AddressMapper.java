package com.lushihao.sharewe.dao;

import com.lushihao.sharewe.entity.userinfo.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressMapper {

    /**
     * 通过唯一标识获取地址
     *
     * @param id
     * @return
     */
    Address findById(int id);

    /**
     * 获取用户下的地址集合
     *
     * @param openId
     * @return
     */
    List<Map<String, Object>> findByOpenId(String openId);

    /**
     * 创建地址
     *
     * @param address
     * @return
     */
    int createAddress(Address address);

    /**
     * 更新地址
     *
     * @param address
     * @return
     */
    int updateAddress(Address address);

    /**
     * 删除地址
     *
     * @param address
     * @return
     */
    int deleteAddress(Address address);

    /**
     * 删除所有地址
     *
     * @param openId
     * @return
     */
    int deleteAllAddress(String openId);

    /**
     * 修改地址使用数量
     *
     * @param id
     * @return
     */
    int updateAddressUsedCount(int id);

}
