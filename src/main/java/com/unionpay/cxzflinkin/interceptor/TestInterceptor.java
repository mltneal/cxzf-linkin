package com.unionpay.cxzflinkin.interceptor;

import com.alibaba.druid.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestInterceptor implements HandlerInterceptor {
    long start = System.currentTimeMillis();
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        start = System.currentTimeMillis();
        System.out.println("执行preHandle方法-->01");
        String token = httpServletRequest.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            httpServletResponse.setContentType("text/html");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().println("token不存在");
            return false;
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor cost="+(System.currentTimeMillis()-start));
        System.out.println("执行postHandle方法-->02");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("执行afterCompletion方法-->03");
    }
}
