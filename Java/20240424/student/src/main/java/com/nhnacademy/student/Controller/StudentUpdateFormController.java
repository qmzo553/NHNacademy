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
@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
@RequestMapping(value = "/student/update.do", method = RequestMapping.Method.GET)
public class StudentUpdateFormController implements Command {

    //    private StudentRepository studentRepository;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//
//        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String id = req.getParameter("id");
//        Student student = studentRepository.getStudentById(id);
//        if(Objects.isNull(student)){
//            throw new RuntimeException("Student not found :" + id);
//        }
//        req.setAttribute("student",student);
//
//        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
//        rd.forward(req, resp);
//        req.setAttribute("view", "/student/register.jsp");
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        Student student = studentRepository.getStudentById(id);
        if(Objects.isNull(student)){
            throw new RuntimeException("Student not found :" + id);
        }
        req.setAttribute("student",student);

        return "/student/register.jsp";
    }
}
