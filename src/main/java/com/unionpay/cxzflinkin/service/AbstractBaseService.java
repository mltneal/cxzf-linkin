package com.unionpay.cxzflinkin.service;

import com.unionpay.cxzflinkin.doamin.BaseReq;
import com.unionpay.cxzflinkin.doamin.BaseResp;

public abstract  class AbstractBaseService {

    public abstract BaseResp process(BaseReq baseReq);
}
