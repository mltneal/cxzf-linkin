package com.unionpay.cxzflinkin.service;

import com.unionpay.cxzflinkin.domain.BaseReq;
import com.unionpay.cxzflinkin.domain.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 13563
 */
@Service("test")
public class TestService extends AbstractBaseService {


    @Autowired
    MockService mockService;

    @Override
    public BaseResp process(BaseReq baseReq){

        String  s = mockService.dopost();

        System.out.println("返回值" + s);

        return new BaseResp("99",s);
    }

}
