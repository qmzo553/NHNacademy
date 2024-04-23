package com.nhnacademy.hello.filters;

import com.nhnacademy.hello.utils.CounterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class CounterFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(CounterFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CounterUtils.increaseCounter(servletRequest.getServletContext());
        filterChain.doFilter(servletRequest, servletResponse);
        log.error("counter: {}", servletRequest.getServletContext().getAttribute("counter"));
    }
}
