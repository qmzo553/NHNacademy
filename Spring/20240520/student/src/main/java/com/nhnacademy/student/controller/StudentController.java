package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.NotLoginException;
import com.nhnacademy.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    @ModelAttribute("student")
    public Student studentAttributes(@PathVariable("studentId") String id,
                                     @CookieValue(value = "SESSION", required = false) String sessionId) {
        if(!StringUtils.hasText(sessionId)) {
            throw new NotLoginException("로그인이 필요합니다.");
        }
        return studentRepository.getStudent(id);
    }

    @GetMapping(value = "/{studentId}", params = {"!hideScore"})
    public String viewStudent(@ModelAttribute("student") Student student, Model model) {
        model.addAttribute("student", student);
        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@ModelAttribute("student") Student student, Model model) {
        model.addAttribute("student", student);
        return "studentModify";
    }

    @PostMapping("/modify")
    public String modifyUser(@RequestParam("id") String id,
                             @RequestParam("password") String password,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("score") int score,
                             @RequestParam("comment") String comment) {
        studentRepository.modify(id, password, name, email, score, comment);
        return "studentView";
    }
}
