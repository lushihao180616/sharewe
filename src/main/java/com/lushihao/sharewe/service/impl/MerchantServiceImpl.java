package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.dao.MerchantMapper;
import com.lushihao.sharewe.service.MerchantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@Service
@EnableTransactionManagement
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;

}
