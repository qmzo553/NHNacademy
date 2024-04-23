package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "multiServlet", urlPatterns = "/multi")
@Slf4j
public class MultiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("class");
        String url = getServletContext().getInitParameter("url");

        try (PrintWriter out = resp.getWriter()) {
            out.println(String.join(",", values));
            out.printf("url:%s\n",url);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
