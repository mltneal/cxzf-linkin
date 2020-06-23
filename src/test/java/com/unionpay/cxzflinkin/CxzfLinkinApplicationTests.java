package com.unionpay.cxzflinkin;

import com.google.gson.Gson;
import com.unionpay.cxzflinkin.bootstrap.SpringContext;
import com.unionpay.cxzflinkin.domain.BaseReq;
import com.unionpay.cxzflinkin.service.MockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
class CxzfLinkinApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    Gson gson;


    @Mock
    MockService mockService;

    @BeforeEach
    public void initSpring(){
        SpringContext.init(context);
    }


    @Test
    public void contextLoads() {
        System.out.println("ok");
    }

    @Test
    public void test1() throws Exception {
        BaseReq baseReq = new BaseReq();
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        doReturn("模拟返回的字符串").when(mockService).dopost();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/test")
                .content(gson.toJson(baseReq))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("respCode").exists())
                .andExpect(jsonPath("respCode").value("99"))
                .andDo(print())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }



}
