package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.Student;

public interface StudentRepository {
    boolean exists(String id);

    Student register(String id, String password, String name, String email, int score, String comment);

    Student getStudent(String id);

    boolean matches(String id, String password);
}
