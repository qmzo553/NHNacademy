package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.user.User;
import com.nhnacademy.customerservice.service.user.UserService;
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

    public static final String LOGIN_COOKIE_NAME = "SESSION";
    private final UserService userService;

    @GetMapping("/login")
    public String login(@CookieValue(value = LOGIN_COOKIE_NAME, required = false) String id) {
        if (StringUtils.hasText(id)) {
            User user = userService.getUser(id);
            if(user.getRole().equals(User.Role.USER)) {
                return "redirect:/cs/" + user.getId();
            } else if(user.getRole().equals(User.Role.MANAGER)) {
                return "redirect:/cs/admin/";
            }
        }
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletResponse response) {
        if (userService.doLogin(id, pwd)) {
            Cookie cookie = null;
            User user = userService.getUser(id);
            cookie = new Cookie(LOGIN_COOKIE_NAME, id);
            response.addCookie(cookie);

            if(user.getRole().equals(User.Role.USER)) {
                return "redirect:/cs/" + id;
            } else if(user.getRole().equals(User.Role.MANAGER)) {
                return "redirect:/cs/admin/";
            }
        }

        return "redirect:/cs/login";
    }

    @GetMapping("/logout")
    public String doLogout(HttpServletResponse response) {
        Cookie cookie = new Cookie(LOGIN_COOKIE_NAME, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/cs/login";
    }
}
