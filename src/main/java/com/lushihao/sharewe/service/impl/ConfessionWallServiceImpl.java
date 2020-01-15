package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.entity.confession.ConfessionWall;
import com.lushihao.sharewe.service.ConfessionWallService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service
@EnableTransactionManagement
public class ConfessionWallServiceImpl implements ConfessionWallService {

    @Override
    public String sendWall(ConfessionWall confessionWall) {
        return null;
    }

}
