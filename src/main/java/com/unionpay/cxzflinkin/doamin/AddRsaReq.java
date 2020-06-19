package com.unionpay.cxzflinkin.doamin;

import lombok.Data;

@Data
public class AddRsaReq {

    private String appId;
    private String pubKey;
    private String priKey;

}
