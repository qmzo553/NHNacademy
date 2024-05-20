package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.domain.StudentRegisterRequest;
import com.nhnacademy.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    @GetMapping
    public String showRegisterForm(Model model) {
        return "studentRegister";
    }

    @PostMapping
    public ModelAndView registerStudent(@ModelAttribute StudentRegisterRequest studentRegisterRequest) {
        Student student = studentRepository.register(
                studentRegisterRequest.getId(),
                studentRegisterRequest.getPassword(),
                studentRegisterRequest.getName(),
                studentRegisterRequest.getEmail(),
                studentRegisterRequest.getScore(),
                studentRegisterRequest.getComment());

        ModelAndView modelAndView = new ModelAndView("redirect:/student/" + student.getId());
        modelAndView.addObject("studentId", student.getId());

        return modelAndView;
    }

}
