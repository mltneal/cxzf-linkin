package com.unionpay.cxzflinkin.service;

import com.unionpay.cxzflinkin.domain.BaseReq;
import com.unionpay.cxzflinkin.domain.BaseResp;

public abstract  class AbstractBaseService {

    public abstract BaseResp process(BaseReq baseReq);
}
