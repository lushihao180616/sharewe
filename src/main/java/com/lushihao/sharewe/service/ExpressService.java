package com.lushihao.sharewe.service;

import com.lushihao.sharewe.entity.express.Express;

public interface ExpressService {

    String sendExpress(Express express);

    String getExpresses(int buildingId, int express_lastId);

    String getExpress(Express express);

    String sendExpressReward(Express express);

    String payExpressReward(Express express);

    String removeExpress(int expressId);

    String getSendExpress(String sendUserOpenId, int statusId);

    String getGetExpress(String getUserOpenId, int statusId);

    String sendCancleExpress(int expressId, boolean sendUserCancle);

    String getCancleExpress(int expressId, String getUserOpenId);

    String getCompleteExpress(int expressId, boolean getUserComplete);

    String sendCompleteExpress(int expressId, int reward, String sendUserOpenId, String getUserOpenId, int addressId);

}
