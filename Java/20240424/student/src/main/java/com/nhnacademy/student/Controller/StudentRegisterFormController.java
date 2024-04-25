package com.nhnacademy.student.Controller;


import com.nhnacademy.student.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.GET)
public class StudentRegisterFormController implements Command {

    //    private StudentRepository studentRepository;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
//        rd.forward(req, resp);
//        req.setAttribute("view", "/student/register.jsp");
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        return "/student/register.jsp";
    }
}
