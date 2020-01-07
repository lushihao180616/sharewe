package com.lushihao.sharewe.controller;

import com.lushihao.myutils.json.LSHJsonUtils;
import com.lushihao.sharewe.entity.Address;
import com.lushihao.sharewe.service.AddressService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 保存地址
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/saveAddress")
    public @ResponseBody
    String saveAddress(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody String data) {
        Address address = LSHJsonUtils.json2Bean(data, Address.class);

        return addressService.createAddress(address);
    }

    /**
     * 修改地址
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/modifyAddress")
    public @ResponseBody
    String modifyAddress(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody String data) {
        Address address = LSHJsonUtils.json2Bean(data, Address.class);

        return addressService.updateAddress(address);
    }

    /**
     * 删除地址
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "/deleteAddress")
    public @ResponseBody
    String deleteAddress(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody String data) {
        Address address = LSHJsonUtils.json2Bean(data, Address.class);

        return addressService.deleteAddress(address);
    }
}
