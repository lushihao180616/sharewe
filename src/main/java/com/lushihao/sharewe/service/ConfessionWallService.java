package com.lushihao.sharewe.service;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.entity.confession.ConfessionWall;

public interface ConfessionWallService {

    LSHResponse sendWall(ConfessionWall confessionWall);

}
