package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.domain.inquiry.InquiryRequest;
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

import static com.nhnacademy.customerservice.controller.AccountController.CUSTOMER_COOKIE_NAME;
import static com.nhnacademy.customerservice.controller.AccountController.LOGIN_COOKIE_NAME;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CustomerController {

    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;

    @GetMapping("/{id}")
    public String index(@PathVariable String id, Model model) {
        List<Inquiry> inquiryList = inquiryRepository.getInquiryListByCustomerId(id);
        model.addAttribute("inquiryList", inquiryList);
        return "customer/index";
    }

    @GetMapping("/inquiry")
    public String inquiry(@CookieValue(value = LOGIN_COOKIE_NAME, required = false) String cookieId, Model model) {
        String id = cookieId.replace(CUSTOMER_COOKIE_NAME, "");
        model.addAttribute("id", id);
        return "customer/inquiry";
    }

    @PostMapping("/inquiry")
    public String doInquiry(@Valid @ModelAttribute InquiryRequest inquiryRequest,
                            @CookieValue(value = LOGIN_COOKIE_NAME, required = false) String cookieId,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        String id = cookieId.replace(CUSTOMER_COOKIE_NAME, "");
        Inquiry inquiry = Inquiry.create(inquiryRequest.getTitle(),
                inquiryRequest.getContent(),
                id,
                Inquiry.Category.valueOf(inquiryRequest.getCategory()));

        inquiryRepository.saveInquiry(inquiry, id);
        return "redirect:/cs/" + id;
    }

    @GetMapping("/inquiry/{inquiryId}")
    public String showInquiry(@PathVariable Long inquiryId,
                              @CookieValue(value = LOGIN_COOKIE_NAME, required = false) String cookieId,
                              Model model) {
        String id = cookieId.replace(CUSTOMER_COOKIE_NAME, "");

        Inquiry inquiry = inquiryRepository.getInquiryById(inquiryId);

        if(inquiry.isAnswerStatus()) {
            model.addAttribute("answer", answerRepository.getAnswer(inquiryId));
        }

        model.addAttribute("id", id);
        model.addAttribute("inquiry", inquiry);
        return "customer/inquiry_detail";
    }
}
