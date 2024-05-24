package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.user.User;
import com.nhnacademy.customerservice.repository.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class AccountController {

    public static final String CUSTOMER_COOKIE_NAME = "customer";
    public static final String MANAGER_COOKIE_NAME = "manager";
    public static final String LOGIN_COOKIE_NAME = "SESSION";
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login(@CookieValue(value = LOGIN_COOKIE_NAME, required = false) String cookieId) {
        if (StringUtils.hasText(cookieId)) {
            if(cookieId.contains(CUSTOMER_COOKIE_NAME)) {
                String id = cookieId.replace(CUSTOMER_COOKIE_NAME, "");
                return "redirect:/cs/" + id;
            } else if(cookieId.contains(MANAGER_COOKIE_NAME)) {
                return "redirect:/cs/admin/";
            }
        }
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletResponse response) {
//        if (userRepository.matches(id, pwd)) {
//            Cookie cookie = null;
//            User user = userRepository.getUser(id);
//
//            if(user instanceof Customer) {
//                cookie = new Cookie(LOGIN_COOKIE_NAME, CUSTOMER_COOKIE_NAME + id);
//                response.addCookie(cookie);
//                return "redirect:/cs/" + id;
//
//            } else if(user instanceof Manager) {
//                cookie = new Cookie(LOGIN_COOKIE_NAME, MANAGER_COOKIE_NAME + id);
//                response.addCookie(cookie);
//                return "redirect:/cs/admin/";
//            }
//        }

        return "redirect:/cs/login";
    }

    @GetMapping("/logout")
    public String doLogout(@CookieValue(value = "SESSION", required = false) String id,
                           HttpServletResponse response) {
        Cookie cookie = new Cookie(LOGIN_COOKIE_NAME, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/cs/login";
    }
}
