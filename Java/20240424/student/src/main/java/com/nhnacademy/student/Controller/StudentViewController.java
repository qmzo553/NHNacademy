package com.nhnacademy.student.Controller;

import com.nhnacademy.student.RequestMapping;
import com.nhnacademy.student.Student;
import com.nhnacademy.student.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
@RequestMapping(value = "/student/view.do", method = RequestMapping.Method.GET)
public class StudentViewController implements Command {

//    private StudentRepository studentRepository;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
//    }
//
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String id = req.getParameter("id");
//        log.info("id : {}", id);
//        if(Objects.isNull(id)){
//            throw new RuntimeException("parameter [id] : null ");
//        }
//
//        Student student = studentRepository.getStudentById(id);
//        if(Objects.isNull(student)){
//            throw new IllegalArgumentException("student [id] : null ");
//        }
//
//        log.error("student:{}", student);
//        req.setAttribute("student",student);
//
//        RequestDispatcher rd = req.getRequestDispatcher("/student/view.jsp");
//        rd.forward(req, resp);
//        req.setAttribute("view", "/student/view.jsp");
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        log.info("id : {}", id);
        if(Objects.isNull(id)){
            throw new RuntimeException("parameter [id] : null ");
        }

        Student student = studentRepository.getStudentById(id);
        if(Objects.isNull(student)){
            throw new IllegalArgumentException("student [id] : null ");
        }

        log.error("student:{}", student);
        req.setAttribute("student",student);

        return "/student/view.jsp";
    }
}
