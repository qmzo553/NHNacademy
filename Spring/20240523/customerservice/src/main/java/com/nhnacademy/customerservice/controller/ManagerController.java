package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.repository.answer.AnswerRepository;
import com.nhnacademy.customerservice.repository.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/admin")
public class ManagerController {

    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Inquiry> noAnswerYetList = inquiryRepository.getNoAnswerYet();
        model.addAttribute("inquiryList", noAnswerYetList);
        return "manager/index";
    }

    @GetMapping("/answer")
    public String answer() {
        return null;
    }

    @PostMapping("/answer")
    public String doAnswer() {
        return null;
    }
}
