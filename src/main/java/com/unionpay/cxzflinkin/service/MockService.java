package com.unionpay.cxzflinkin.service;


import org.springframework.stereotype.Service;

@Service("mock")
public class MockService {

    public String dopost(){
        System.out.println("mock method dopost");
        return "返回的字符串";
    }

}
