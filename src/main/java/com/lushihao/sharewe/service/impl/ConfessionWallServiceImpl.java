package com.lushihao.sharewe.service.impl;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.ConfessionWallItemMapper;
import com.lushihao.sharewe.dao.ConfessionWallMapper;
import com.lushihao.sharewe.entity.confession.ConfessionWall;
import com.lushihao.sharewe.service.ConfessionWallService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@EnableTransactionManagement
public class ConfessionWallServiceImpl implements ConfessionWallService {

    @Resource
    private ConfessionWallMapper confessionWallMapper;
    @Resource
    private ConfessionWallItemMapper confessionWallItemMapper;

    /**
     * 发送告白墙
     *
     * @param confessionWall
     * @return
     */
    @Override
    @Transactional
    public LSHResponse sendWall(ConfessionWall confessionWall) {
        int sql_back = confessionWallMapper.createConfessionWall(confessionWall);
        confessionWallItemMapper.batchCreateConfessionWallItem(confessionWall.getNeedInfoList());
        if (sql_back == 0) {
            return new LSHResponse("告白墙发送失败，请稍后再试");
        } else {
            return new LSHResponse((Map<String, Object>) null);
        }
    }

}
