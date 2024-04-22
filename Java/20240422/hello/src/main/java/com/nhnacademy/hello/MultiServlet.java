package com.nhnacademy.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MultiServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(MultiServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("class");

        try (PrintWriter out = resp.getWriter()) {
            out.println(String.join(",", values));
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
