package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/admin")
public class ManagerController {

    private UserRepository userRepository;

    @GetMapping("/")
    public String managerMain() {
        return null;
    }

//    @GetMapping("/answer")
//    public String answer() {
//        return null;
//    }

    @PostMapping("/answer")
    public String answer() {
        return null;
    }
}
