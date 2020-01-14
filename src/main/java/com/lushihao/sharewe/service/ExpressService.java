package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.express.Express;

public interface ExpressService {

    String sendExpress(Express express);

    String getExpress(int buildingId, int typeId, int express_lastId);

}
