package com.nhnacademy.student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "errorServlet", urlPatterns = "/error")
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
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }
}
