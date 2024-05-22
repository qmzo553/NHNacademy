package com.nhnacademy.student.controller;

import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class StudentLoginController {
    private final StudentRepository studentRepository;

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
        Cookie cookie = new Cookie("SESSION", null);
        response.addCookie(cookie);

        return "redirect:/login";
    }
}
