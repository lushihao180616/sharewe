package com.lushihao.sharewe.service.impl;

import com.lushihao.sharewe.entity.confession.ConfessionWall;
import com.lushihao.sharewe.service.ConfessionWallService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableTransactionManagement
public class ConfessionWallServiceImpl implements ConfessionWallService {

    /**
     * 发送告白墙
     *
     * @param confessionWall
     * @return
     */
    @Override
    @Transactional
    public String sendWall(ConfessionWall confessionWall) {
        return null;
    }

}
