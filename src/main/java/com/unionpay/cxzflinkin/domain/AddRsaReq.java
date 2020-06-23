package com.unionpay.cxzflinkin.domain;

import lombok.Data;

@Data
public class AddRsaReq {

    private String appId;
    private String pubKey;
    private String priKey;

}
