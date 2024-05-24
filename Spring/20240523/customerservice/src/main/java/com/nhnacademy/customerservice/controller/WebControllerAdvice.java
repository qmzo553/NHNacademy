package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.exception.AnswerNotFoundException;
import com.nhnacademy.customerservice.exception.InquiryNotFoundException;
import com.nhnacademy.customerservice.exception.UserNotFoundException;
import com.nhnacademy.customerservice.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class WebControllerAdvice {

    private final String MASSAGE_KEY = "message";
    private final String STATUS_KEY = "status";
    private final String ERROR_PAGE = "error";

    @ExceptionHandler({UserNotFoundException.class,
            InquiryNotFoundException.class,
            AnswerNotFoundException.class})
    public String handleCustomerNotFoundException(UserNotFoundException ex, Model model) {
        log.error("UserNotFoundException", ex);
        model.addAttribute(MASSAGE_KEY, ex.getMessage());
        model.addAttribute(STATUS_KEY, HttpStatus.NOT_FOUND);
        return ERROR_PAGE;
    }

    @ExceptionHandler({IllegalArgumentException.class, ValidationFailedException.class})
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        log.error("IllegalArgumentException", ex);
        model.addAttribute(MASSAGE_KEY, ex.getMessage());
        model.addAttribute(STATUS_KEY, HttpStatus.BAD_REQUEST);
        return ERROR_PAGE;
    }

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException ex, Model model) {
        log.error("IOException", ex);
        model.addAttribute(MASSAGE_KEY, ex.getMessage());
        model.addAttribute(STATUS_KEY, HttpStatus.INTERNAL_SERVER_ERROR);
        return ERROR_PAGE;
    }
}
