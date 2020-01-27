package com.lushihao.sharewe.dao.express;

import com.lushihao.sharewe.entity.express.ExpressEvaluate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExpressEvaluateMapper {

    int sendExpressEvaluate(ExpressEvaluate expressEvaluate);

}
