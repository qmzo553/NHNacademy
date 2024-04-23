package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "counterServlet", urlPatterns = "/counter", initParams = {@WebInitParam(name="counter",value="100")})
@Slf4j
public class CounterServlet extends HttpServlet {
    private long counter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        counter = Optional.ofNullable(config.getInitParameter("counter"))
                .map(Long::parseLong)
                .orElse(0L);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        try(PrintWriter writer = resp.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>Counter: " + counter + "</h1>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
