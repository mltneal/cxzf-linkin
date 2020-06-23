package com.unionpay.cxzflinkin.domain;


import lombok.Data;

@Data
public class BaseResp {


    String respCode;

    String respMsg;

    public BaseResp(String respCode, String respMsg){
        this.respCode = respCode;
        this.respMsg = respMsg;
    }
}
