package com.nhnacademy.student;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Random;

@WebListener
public class WebApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();
        Random r = new Random();

        for(int i = 1; i <= 10; i++) {
            int age = r.nextInt(30 - 20) + 20;
            Student student = new Student(i + "", "name", Gender.M, age);
            studentRepository.save(student);
        }

        context.setAttribute("studentRepository", studentRepository);
    }
}
