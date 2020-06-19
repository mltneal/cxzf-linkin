package com.unionpay.cxzflinkin.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 13563
 */

@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "testFilter")
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("TestFilter Execute cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
