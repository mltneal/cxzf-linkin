package com.unionpay.cxzflinkin.bootstrap;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class SpringContext {

    private static ApplicationContext context;

    public static void init(ApplicationContext wac){
        context = wac;
    }

    public static <T> T getBean(String name){
        return (T) context.getBean(name);
    }

    public static <T> T getBean(String name,Class<T> tClass){
        return (T) context.getBean(name,tClass);
    }

    public static <T> T getBean(Class<T> tClass){
        return (T) context.getBean(tClass);
    }
}
