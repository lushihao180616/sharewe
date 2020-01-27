package com.lushihao.sharewe.service.express;

import com.lushihao.myutils.response.vo.LSHResponse;
import com.lushihao.sharewe.entity.express.Express;
import com.lushihao.sharewe.entity.express.ExpressEvaluate;

public interface ExpressService {

    LSHResponse sendExpress(Express express, int originalAddressId);

    LSHResponse getExpresses(int buildingId, int express_lastId);

    LSHResponse getExpress(Express express);

    LSHResponse sendExpressReward(Express express);

    LSHResponse payExpressReward(Express express);

    LSHResponse removeExpress(int expressId);

    LSHResponse getSendExpress(String sendUserOpenId, int statusId);

    LSHResponse getGetExpress(String getUserOpenId, int statusId);

    LSHResponse sendCancleExpress(int expressId, boolean sendUserCancle);

    LSHResponse getCancleExpress(int expressId, String getUserOpenId);

    LSHResponse getCompleteExpress(int expressId, boolean getUserComplete);

    LSHResponse sendCompleteExpress(int expressId, int reward, String sendUserOpenId, String getUserOpenId, int addressId);

    LSHResponse sendExpressEvaluate(ExpressEvaluate expressEvaluate, int expressId);
}
