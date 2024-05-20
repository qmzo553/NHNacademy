package com.nhnacademy.student.controller;

import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class StudentLoginController {
    private final StudentRepository studentRepository;

    @GetMapping
    public String login(@CookieValue(value = "SESSION", required = false) String id,
                        HttpServletRequest request) {
        if (StringUtils.hasText(id)) {
            return "redirect:/student/" + id;
        } else {
            return "loginForm";
        }
    }

    @PostMapping
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {
        if (studentRepository.matches(id, pwd)) {
            HttpSession session = request.getSession(true);

            Cookie cookie = new Cookie("SESSION", id);
            response.addCookie(cookie);

            modelMap.put("id", id);
            return "redirect:/student/" + id;
        } else {
            return "redirect:/login";
        }
    }
}
