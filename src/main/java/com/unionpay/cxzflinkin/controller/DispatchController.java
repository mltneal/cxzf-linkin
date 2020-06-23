package com.unionpay.cxzflinkin.controller;

import com.unionpay.cxzflinkin.bootstrap.SpringContext;
import com.unionpay.cxzflinkin.domain.BaseReq;
import com.unionpay.cxzflinkin.domain.BaseResp;
import com.unionpay.cxzflinkin.service.AbstractBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
public class DispatchController {

    @RequestMapping("api/{serviceId}")
    public BaseResp test(@PathVariable()final String serviceId , @RequestBody BaseReq baseReq){

            return dispatch(serviceId,baseReq);

    }

    public BaseResp dispatch(String serviceId,BaseReq baseReq){
        return SpringContext.getBean(serviceId, AbstractBaseService.class).process(baseReq);
    }

}
