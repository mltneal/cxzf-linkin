package com.unionpay.cxzflinkin.controller;


import com.google.gson.Gson;
import com.unionpay.cxzflinkin.bean.KeyInfoBean;
import com.unionpay.cxzflinkin.domain.AddRsaReq;
import com.unionpay.cxzflinkin.domain.BaseResp;
import com.unionpay.cxzflinkin.service.KeyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("rsa")
public class RSAController {

    @Autowired
    private KeyInfoService keyInfoService;

    @Autowired
    Gson gson;

    @PostMapping("submit")
    public BaseResp submit(@RequestBody AddRsaReq addRsaReq){
        log.info("插入数据{}",gson.toJson(addRsaReq));
        KeyInfoBean keyInfoBean =new KeyInfoBean();
        BeanUtils.copyProperties(addRsaReq,keyInfoBean);
        try{
            int re = keyInfoService.insertKey(keyInfoBean);
            if(re == 1){
                return new BaseResp("1","success");
            }else {
                return new BaseResp("0","failed");
            }
        }catch (Exception e){
            log.info("录入rsa信息失败,出错{}",e);
            return new BaseResp("0","failed"+e);
        }


    }
}
