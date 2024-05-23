package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.domain.answer.AnswerRequest;
import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.exception.ValidationFailedException;
import com.nhnacademy.customerservice.repository.answer.AnswerRepository;
import com.nhnacademy.customerservice.repository.inquiry.InquiryRepository;
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

    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Inquiry> noAnswerYetList = inquiryRepository.getNoAnswerYet();
        model.addAttribute("inquiryList", noAnswerYetList);
        return "manager/index";
    }

    @GetMapping("/answer/{inquiryId}")
    public String answer(@PathVariable Long inquiryId, Model model) {
        Inquiry inquiry = inquiryRepository.getInquiryById(inquiryId);
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
        Inquiry inquiry = inquiryRepository.getInquiryById(inquiryId);
        inquiry.setAnswerStatus(true);
        Answer answer = Answer.create(answerRequest.getContent(), answerRequest.getManagerId());
        answerRepository.saveAnswer(answer, inquiryId);
        return "redirect:/cs/admin/";
    }
}
