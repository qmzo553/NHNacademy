package com.nhnacademy.student;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try{
            //실제 요청 처리할 servlet을 결정
            String servletPath = resolveServlet(req.getServletPath());
            RequestDispatcher rd = req.getRequestDispatcher(servletPath);
            rd.include(req, resp);

            //실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해 줌.
            String view = (String) req.getAttribute("view");
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length()));

                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        }catch(Exception ex){

            log.error("", ex);
            req.setAttribute("exception", ex);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private String resolveServlet(String servletPath) {
        String processingServlet = null;

        if("/student/list.do".equals(servletPath)) {
            processingServlet = "/student/list";
        } else if("/student/register.do".equals(servletPath)) {
            processingServlet = "/student/register";
        } else if("/student/view.do".equals(servletPath)) {
            processingServlet = "/student/view";
        } else if("/student/update.do".equals(servletPath)) {
            processingServlet = "/student/update";
        } else if("/student/delete.do".equals(servletPath)) {
            processingServlet = "/student/delete";
        }

        return processingServlet;
    }
}
