package com.nhnacademy.customerservice.controller;

import com.nhnacademy.customerservice.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class WebControllerAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    public String handleCustomerNotFoundException(CustomerNotFoundException ex, Model model) {
        log.error("CustomerNotFoundException", ex);
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.NOT_FOUND);
        return "error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        log.error("IllegalArgumentException", ex);
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.BAD_REQUEST);
        return "error";
    }
}
