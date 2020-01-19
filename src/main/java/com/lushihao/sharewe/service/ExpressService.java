package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.express.Express;

public interface ExpressService {

    String sendExpress(Express express);

    String getExpresses(int buildingId, int express_lastId);

    String getExpress(Express express);

    String removeExpress(int expressId);

}
