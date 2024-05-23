package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CustomerController {

    private UserRepository userRepository;

    @GetMapping("/")
    public String customerMain() {
        return null;
    }

//    @GetMapping("/inquiry")
//    public String customerInquiry() {
//        return null;
//    }

    @PostMapping("/inquiry")
    public String customerInquiry() {
        return null;
    }
}
