package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.repository.CustomerRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final CustomerRepository customerRepository;

    @GetMapping("/login")
    public String login(@CookieValue(value = "SESSION", required = false) String id) {
        if (StringUtils.hasText(id)) {
            return "redirect:/student/" + id;
        } else {
            return "loginForm";
        }
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        if (studentRepository.matches(id, pwd)) {
            HttpSession session = request.getSession(true);

            Cookie cookie = new Cookie("SESSION", id);
            response.addCookie(cookie);

            return "redirect:/student/" + id;
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String doLogout(@CookieValue(value = "SESSION", required = false) String id,
                           HttpServletResponse response) {
        Cookie cookie = new Cookie("SESSION", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/login";
    }
}
