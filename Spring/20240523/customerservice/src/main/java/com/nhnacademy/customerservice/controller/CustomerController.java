package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CustomerController {

    private UserRepository userRepository;

    @GetMapping("/{id}")
    public String index(@PathVariable String id, Model model) {
        return null;
    }

//    @GetMapping("/inquiry")
//    public String customerInquiry() {
//        return null;
//    }

    @PostMapping("/inquiry")
    public String doInquiry() {
        return null;
    }
}
