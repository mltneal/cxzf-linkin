package com.unionpay.cxzflinkin.bootstrap;

import com.unionpay.cxzflinkin.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 13563
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径和排除拦截路径
        /*  - /**： 匹配所有路径
            - /admin/**：匹配 /admin/ 下的所有路径
            - /secure/*：只匹配 /secure/user，不匹配 /secure/user/info
         */
        registry.addInterceptor(new TestInterceptor()) // 添加拦截器
                .addPathPatterns("/test/**") // 添加拦截路径
                .excludePathPatterns(// 添加排除拦截路径
                        "/hello").order(0);//执行顺序
        super.addInterceptors(registry);
    }

}
