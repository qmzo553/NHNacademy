package com.nhnacademy.student.controller;

import com.nhnacademy.student.exception.NotLoginException;
import com.nhnacademy.student.exception.StudentAlreadyExistsException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class WebControllerAdvice {

    @ExceptionHandler(ValidationFailedException.class)
    public String handleValidationFailedException(ValidationFailedException e, Model model) {
        log.error("Student Validation Failed", e);
        model.addAttribute("exception", e);
        model.addAttribute("status", HttpStatus.BAD_REQUEST);
        return "error";
    }

    @ExceptionHandler(NotLoginException.class)
    public String handleNotLoginException(NotLoginException e, Model model) {
        log.error("Student Not Login", e);
        model.addAttribute("exception", e);
        model.addAttribute("status", HttpStatus.UNAUTHORIZED);
        return "error";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public String handleStudentNotFoundException(StudentNotFoundException e, Model model) {
        log.error("Student Not Found", e);
        model.addAttribute("exception", e);
        model.addAttribute("status", HttpStatus.NOT_FOUND);
        return "error";
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public String handleStudentAlreadyExistsException(StudentAlreadyExistsException e, Model model) {
        log.error("Student Already Exists", e);
        model.addAttribute("exception", e);
        model.addAttribute("status", HttpStatus.CONFLICT);
        return "error";
    }
}
