package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.dao.ExpressMapper;
import com.lushihao.sharewe.dao.PurchaseMapper;
import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.service.ExpressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@Service
@EnableTransactionManagement
public class ExpressServiceImpl implements ExpressService {

    @Resource
    private ExpressMapper expressMapper;

    @Override
    public String sendExpress(Express express) {
        return null;
    }

}
