package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.dao.MerchantMapper;
import com.lushihao.sharewe.service.MerchantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;

}
