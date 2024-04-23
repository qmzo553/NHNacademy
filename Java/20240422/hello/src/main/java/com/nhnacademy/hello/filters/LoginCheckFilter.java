package com.nhnacademy.hello.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@WebFilter(
        filterName = "loginFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclude-urls", value = "/login\n" +
                        "                /logout\n" +
                        "                /login.html")
        }
)
public class LoginCheckFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(LoginCheckFilter.class.getName());
    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude-urls");
        log.error("exclude-urls: " + urls);
        Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .forEach(excludeUrls::add);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();

        if(!excludeUrls.contains(requestUri)) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
            if(Objects.isNull(session)) {
                ((HttpServletResponse) servletResponse).sendRedirect("/login.html");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
