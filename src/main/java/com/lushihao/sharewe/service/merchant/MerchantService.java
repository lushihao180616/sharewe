package com.lushihao.sharewe.service.merchant;

import com.lushihao.myutils.response.vo.LSHResponse;

public interface MerchantService {

    LSHResponse getMerchantInfo(String merchantCode);

    LSHResponse getMerchants();

    LSHResponse createUserToMerchant(String openId) throws Exception;
}
