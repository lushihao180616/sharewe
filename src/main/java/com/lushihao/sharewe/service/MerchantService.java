package com.lushihao.sharewe.service;

import com.lushihao.myutils.response.vo.LSHResponse;

public interface MerchantService {

    LSHResponse getMerchantInfo(String merchantCode);

    LSHResponse getMerchants();

}
