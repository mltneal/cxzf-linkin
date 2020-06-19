package com.unionpay.cxzflinkin.controller;


import com.unionpay.cxzflinkin.bean.KeyInfoBean;
import com.unionpay.cxzflinkin.service.KeyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {

    @Autowired
    private KeyInfoService keyInfoService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Admin");
        return "test";
    }

    @RequestMapping("hello")
    @ResponseBody
    public String insert(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        return "hello";
    }

    @RequestMapping("test")
    public String test(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        return "test";
    }
}
