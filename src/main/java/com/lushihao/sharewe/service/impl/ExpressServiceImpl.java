package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.dao.ExpressMapper;
import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.service.ExpressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@EnableTransactionManagement
public class ExpressServiceImpl implements ExpressService {

    @Resource
    private ExpressMapper expressMapper;

    /**
     * 发送快递
     *
     * @param express
     * @return
     */
    @Override
    @Transactional
    public String sendExpress(Express express) {
        return null;
    }

}
