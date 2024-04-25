package com.nhnacademy.student.Controller;

import com.nhnacademy.student.Gender;
import com.nhnacademy.student.RequestMapping;
import com.nhnacademy.student.Student;
import com.nhnacademy.student.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
@RequestMapping(value = "/student/update.do", method = RequestMapping.Method.POST)
public class StudentUpdateController implements Command {

//    private StudentRepository studentRepository;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//
//        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
//    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//        Gender gender = null;
//
//        if(Objects.nonNull(req.getParameter("gender"))){
//            gender = Gender.valueOf(req.getParameter("gender"));
//        }
//
//        Integer age = null;
//        if(Objects.nonNull(req.getParameter("age"))){
//            age = Integer.parseInt(req.getParameter("age"));
//        }
//
//        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
//            throw new RuntimeException("id,name,gender,age 확인해주세요!");
//        }
//
//        Student student = new Student(id,name,gender,age);
//        studentRepository.update(student);
//        log.info("update student {}", student);
//
//        resp.sendRedirect("/student/view?id=" + student.getId());
//        req.setAttribute("view", "redirect:/student/view.do?id=" + student.getId());
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = null;

        if(Objects.nonNull(req.getParameter("gender"))){
            gender = Gender.valueOf(req.getParameter("gender"));
        }

        Integer age = null;
        if(Objects.nonNull(req.getParameter("age"))){
            age = Integer.parseInt(req.getParameter("age"));
        }

        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
            throw new RuntimeException("id,name,gender,age 확인해주세요!");
        }

        Student student = new Student(id,name,gender,age);
        studentRepository.update(student);
        log.info("update student {}", student);

        return "redirect:/student/view.do?id=" + student.getId();
    }
}
