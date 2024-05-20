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
    public String login(@CookieValue(value = "SESSION", required = false) String sessionId,
                        HttpServletRequest request) {
        if (StringUtils.hasText(sessionId)) {
            HttpSession session = request.getSession();
            String id = (String) session.getAttribute("id");
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

            Cookie cookie = new Cookie("SESSION", session.getId());
            response.addCookie(cookie);

            modelMap.put("id", id);
            return "redirect:/student/" + id;
        } else {
            return "redirect:/login";
        }
    }
}
