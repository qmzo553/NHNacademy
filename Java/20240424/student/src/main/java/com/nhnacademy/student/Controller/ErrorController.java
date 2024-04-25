package com.nhnacademy.student.Controller;

import com.nhnacademy.student.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.RequestDispatcher.*;

@WebServlet(name = "errorServlet", urlPatterns = "/error")
@RequestMapping(value = "/student/error.do", method = RequestMapping.Method.GET)
public class ErrorController implements Command {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
//        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
//        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
//        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
//        req.setAttribute("request_uri", req.getRequestURI());
//
//        RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
//        rd.forward(req, resp);
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
        req.setAttribute("request_uri", req.getRequestURI());
        return "/error.jsp";
    }
}
