package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.domain.StudentRequest;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    @ModelAttribute("student")
    public Student studentAttributes(@PathVariable("studentId") String id) {
        return studentRepository.getStudent(id);
    }

    @GetMapping(value = "/{studentId}", params = {"!hideScore"})
    public String viewStudent(@ModelAttribute("student") Student student, Model model) {
        Student maskStudent = Student.constructPasswordMaskedStudent(student);
        model.addAttribute("student", maskStudent);
        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@ModelAttribute("student") Student student, Model model) {
        model.addAttribute("student", student);
        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyUser(@Valid @ModelAttribute("studentRequest") StudentRequest studentRequest,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Student student = studentRepository.modify(
                studentRequest.getId(),
                studentRequest.getPassword(),
                studentRequest.getName(),
                studentRequest.getEmail(),
                studentRequest.getScore(),
                studentRequest.getComment());
        return "redirect:/student/" + student.getId();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandlerFound(NoHandlerFoundException e, Model model) {
        model.addAttribute("exception", e);
        model.addAttribute("status", HttpStatus.NOT_FOUND);
        return "error";
    }

}
