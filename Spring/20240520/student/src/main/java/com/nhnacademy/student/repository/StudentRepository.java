package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.Student;

public interface StudentRepository {
    boolean exists(long id);

    Student register(String name, String email, int score, String comment);

    Student getStudent(long id);
}
