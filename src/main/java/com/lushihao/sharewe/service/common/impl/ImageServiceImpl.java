package com.lushihao.sharewe.service.common.impl;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.dao.common.ImageMapper;
import com.lushihao.sharewe.entity.common.Image;
import com.lushihao.sharewe.service.common.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@EnableTransactionManagement
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageMapper imageMapper;

    /**
     * 添加一条图片记录
     *
     * @param image
     * @return
     */
    @Override
    @Transactional
    public LSHResponse addImage(Image image) {
        int sql_back = imageMapper.addImage(image);
        if (sql_back == 0) {
            return new LSHResponse("添加失败，请稍后再试");
        } else {
            return new LSHResponse((Map<String, Object>) null);
        }
    }

}
