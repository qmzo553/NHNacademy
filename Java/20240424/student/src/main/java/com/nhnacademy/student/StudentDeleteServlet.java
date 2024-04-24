package com.nhnacademy.student;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("id") == null) {
            log.error("해당 id : {} 가 존재하지 않음", req.getParameter("id"));
            throw new RuntimeException();
        }

        String id = req.getParameter("id");
        studentRepository.deleteById(id);
        log.info("Deleted student with id : {}", id);

        // resp.sendRedirect("/student/list");
        req.setAttribute("view", "redirect:/student/list.do");
    }
}
