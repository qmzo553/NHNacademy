package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class StudentHideController {

    private final StudentRepository studentRepository;

    @GetMapping(value = "/student/{studentId}", params = {"hideScore"})
    public String studentHideScore(@PathVariable("studentId") String id,
                                   @RequestParam(name = "hideScore") String hideScore,
                                   Model model) {
        Student student = studentRepository.getStudent(id);
        model.addAttribute("student", student);
        model.addAttribute("hideScore", hideScore != null ? hideScore : "no");
        return "studentView";
    }
}
