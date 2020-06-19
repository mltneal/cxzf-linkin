package com.unionpay.cxzflinkin.doamin;


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
