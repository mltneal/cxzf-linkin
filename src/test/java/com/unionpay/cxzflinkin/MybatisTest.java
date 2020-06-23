package com.unionpay.cxzflinkin;

import com.google.gson.Gson;
import com.unionpay.cxzflinkin.bean.KeyInfoBean;
import com.unionpay.cxzflinkin.bootstrap.SpringContext;
import com.unionpay.cxzflinkin.service.KeyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
class MybatisTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private KeyInfoService keyInfoService;

    @Autowired
    Gson gson;


    @BeforeEach
    public void initSpring(){
        SpringContext.init(context);
    }


    @Test
    public void contextLoads() {
        System.out.println("ok");
    }

    @Test
    public void test() {

        KeyInfoBean keyInfoBean = keyInfoService.findByAppId("111111");
        System.out.println(gson.toJson(keyInfoBean));

    }
    @Test
    public void test2() {

        KeyInfoBean keyInfoBean = keyInfoService.findByApp("111111");
        System.out.println(gson.toJson(keyInfoBean));

    }



}
