package com.unionpay.cxzflinkin.service;

import com.unionpay.cxzflinkin.doamin.BaseReq;
import com.unionpay.cxzflinkin.doamin.BaseResp;
import org.springframework.stereotype.Service;

/**
 * @author 13563
 */
@Service("test")
public class TestService extends AbstractBaseService {

    @Override
    public BaseResp process(BaseReq baseReq){
        return new BaseResp("99","this is test");
    }
}
