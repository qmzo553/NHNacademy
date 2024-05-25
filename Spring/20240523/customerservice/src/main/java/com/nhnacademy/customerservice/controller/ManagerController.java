package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.domain.answer.AnswerRequest;
import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.exception.ValidationFailedException;
import com.nhnacademy.customerservice.service.answer.AnswerService;
import com.nhnacademy.customerservice.service.inquiry.InquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs/admin")
public class ManagerController {

    private final InquiryService inquiryService;
    private final AnswerService answerService;

    @GetMapping("/")
    public String index(Model model) {
        List<Inquiry> noAnswerYetList = inquiryService.getNoAnswerYet();
        model.addAttribute("inquiryList", noAnswerYetList);
        return "manager/index";
    }

    @GetMapping("/answer/{inquiryId}")
    public String answer(@PathVariable Long inquiryId, Model model) {
        Inquiry inquiry = inquiryService.getInquiryByInquiryId(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "manager/inquiry_answer";
    }

    @PostMapping("/answer")
    public String doAnswer(@Valid @ModelAttribute AnswerRequest answerRequest,
                           @RequestParam Long inquiryId,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Inquiry inquiry = inquiryService.getInquiryByInquiryId(inquiryId);
        inquiry.setAnswerStatus(true);
        Answer answer = Answer.create(inquiryId, answerRequest.getContent(), answerRequest.getManagerId());
        inquiryService.updateInquiry(inquiry);
        answerService.saveAnswer(answer);
        return "redirect:/cs/admin/";
    }
}
