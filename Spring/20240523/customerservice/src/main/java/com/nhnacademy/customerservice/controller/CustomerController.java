package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.domain.file.File;
import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.domain.inquiry.InquiryRequest;
import com.nhnacademy.customerservice.exception.ValidationFailedException;
import com.nhnacademy.customerservice.service.answer.AnswerService;
import com.nhnacademy.customerservice.service.file.FileService;
import com.nhnacademy.customerservice.service.inquiry.InquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.nhnacademy.customerservice.controller.AccountController.LOGIN_COOKIE_NAME;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CustomerController {

    private static final String UPLOAD_DIR = "uploads/";
    private final InquiryService inquiryService;
    private final AnswerService answerService;
    private final FileService fileService;

    @GetMapping(value = "/{id}", params = "!category")
    public String index(@PathVariable String id,
                        Model model) {
        List<Inquiry> inquiryList = inquiryService.getInquiriesByUserId(id);

        model.addAttribute("id", id);
        model.addAttribute("inquiryList", inquiryList);
        return "customer/index";
    }

    @GetMapping(value = "/{id}", params = "category")
    public String categoryIndex(@PathVariable String id,
                        @RequestParam("category") String category,
                        Model model) {
        List<Inquiry> inquiryList = null;
        if(category.equals("ALL")) {
            inquiryList = inquiryService.getInquiriesByUserId(id);
        } else {
            inquiryList = inquiryService.getInquiriesByUserIdAndCategory(id, category);
        }

        model.addAttribute("id", id);
        model.addAttribute("inquiryList", inquiryList);
        return "customer/index";
    }

    @GetMapping("/inquiry")
    public String inquiry(@CookieValue(value = LOGIN_COOKIE_NAME, required = false) String id, Model model) {
        model.addAttribute("id", id);
        return "customer/inquiry";
    }

    @PostMapping("/inquiry")
    public String doInquiry(@Valid @ModelAttribute InquiryRequest inquiryRequest,
                            BindingResult bindingResult,
                            @RequestParam("files") List<MultipartFile> files,
                            @CookieValue(value = LOGIN_COOKIE_NAME, required = false) String id
                            ) throws IOException {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Inquiry inquiry = Inquiry.createAuto(inquiryRequest.getTitle(),
                inquiryRequest.getContent(),
                id,
                Inquiry.Category.valueOf(inquiryRequest.getCategory()));

        inquiryService.saveInquiry(inquiry);
        long inquiryId = inquiryService.getLastInquiryId();

        Path uploadDir = Paths.get(UPLOAD_DIR);
        if(!Files.exists(uploadDir)) {
            Files.createDirectory(uploadDir);
        }

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));
            }

            fileService.saveFiles(inquiryId, files);
        }

        return "redirect:/cs/" + id;
    }

    @GetMapping("/inquiry/{inquiryId}")
    public String showInquiry(@PathVariable Long inquiryId,
                              @CookieValue(value = LOGIN_COOKIE_NAME, required = false) String id,
                              Model model) {
        Inquiry inquiry = inquiryService.getInquiryByInquiryId(inquiryId);
        List<File> files = fileService.getFilesByInquiryId(inquiryId);

        if(inquiry.isAnswerStatus()) {
            model.addAttribute("answer", answerService.getAnswer(inquiry.getInquiryId()));
        }

        model.addAttribute("id", id);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("files", files);
        return "customer/inquiry_detail";
    }
}
