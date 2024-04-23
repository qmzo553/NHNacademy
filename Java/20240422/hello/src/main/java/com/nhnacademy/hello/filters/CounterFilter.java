package com.nhnacademy.hello.filters;

import com.nhnacademy.hello.utils.CounterUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(
        filterName = "counterFilter",
        urlPatterns = "/*"
)
@Slf4j
public class CounterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CounterUtils.increaseCounter(servletRequest.getServletContext());
        filterChain.doFilter(servletRequest, servletResponse);
        log.error("counter: {}", servletRequest.getServletContext().getAttribute("counter"));
    }
}
