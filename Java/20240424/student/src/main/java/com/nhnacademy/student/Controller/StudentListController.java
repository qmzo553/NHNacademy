package com.nhnacademy.student.Controller;

import com.nhnacademy.student.RequestMapping;
import com.nhnacademy.student.Student;
import com.nhnacademy.student.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@WebServlet(name = "studentListServlet", urlPatterns = "/student/list")
@RequestMapping(value = "/student/list.do", method = RequestMapping.Method.GET)
public class StudentListController implements Command {

//    private StudentRepository studentRepository;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Student> studentList = studentRepository.getStudents();
//
//        req.setAttribute("studentList", studentList);
//
//        RequestDispatcher rd = req.getRequestDispatcher("/student/list.jsp");
//        rd.forward(req, resp);
//        req.setAttribute("view", "/student/list.jsp");
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList", studentList);

        return "/student/list.jsp";
    }
}
