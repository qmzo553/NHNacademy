package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.repository.inquiry.InquiryRepository;
import com.nhnacademy.customerservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CustomerController {

    private UserRepository userRepository;
    private InquiryRepository inquiryRepository;

    @GetMapping("/{id}")
    public String index(@PathVariable String id, Model model) {
        List<Inquiry> inquiryList = inquiryRepository.getInquiryListByCustomerId(id);
        model.addAttribute("inquiryList", inquiryList);
        return "customer/index";
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
