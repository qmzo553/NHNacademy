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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.nhnacademy.customerservice.controller.AccountController.CUSTOMER_COOKIE_NAME;
import static com.nhnacademy.customerservice.controller.AccountController.LOGIN_COOKIE_NAME;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CustomerController {

    private static final String UPLOAD_DIR = "/src/main/resources/uploads";
    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;

    @GetMapping(value = "/{id}", params = "!{category}")
    public String index(@PathVariable String id,
                        Model model) {
        List<Inquiry> inquiryList = inquiryRepository.getInquiryListByCustomerId(id);

        model.addAttribute("id", id);
        model.addAttribute("inquiryList", inquiryList);
        return "customer/index";
    }

    @GetMapping("/{id}")
    public String categoryIndex(@PathVariable String id,
                        @RequestParam("category") String category,
                        Model model) {
        List<Inquiry> inquiryList = null;
        if(category.equals("ALL")) {
            inquiryList = inquiryRepository.getInquiryListByCustomerId(id);
        } else {
            inquiryList = inquiryRepository.getInquiryListByCustomerIdAndCategory(id, category);
        }

        model.addAttribute("id", id);
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
                            @RequestParam("files") List<MultipartFile> files,
                            @CookieValue(value = LOGIN_COOKIE_NAME, required = false) String cookieId,
                            BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String id = cookieId.replace(CUSTOMER_COOKIE_NAME, "");
        Inquiry inquiry = Inquiry.create(inquiryRequest.getTitle(),
                inquiryRequest.getContent(),
                id,
                Inquiry.Category.valueOf(inquiryRequest.getCategory()),
                files);

        inquiryRepository.saveInquiry(inquiry, id);

        String uploadDirPath = System.getProperty("user.dir") + UPLOAD_DIR;
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Path filePath = Paths.get(uploadDirPath + File.separator + file.getOriginalFilename());
                file.transferTo(filePath);
            }
        }

        return "redirect:/cs/" + id;
    }

    @GetMapping("/inquiry/{inquiryId}")
    public String showInquiry(@PathVariable Long inquiryId,
                              @CookieValue(value = LOGIN_COOKIE_NAME, required = false) String cookieId,
                              Model model) {
//        String id = cookieId.replace(CUSTOMER_COOKIE_NAME, "");
//
//        Inquiry inquiry = inquiryRepository.getInquiryById(inquiryId);
//
//        if(inquiry.isAnswerStatus()) {
//            model.addAttribute("answer", answerRepository.getAnswer(inquiryId));
//        }
//
//        model.addAttribute("id", id);
//        model.addAttribute("inquiry", inquiry);
        return "customer/inquiry_detail";
    }
}
