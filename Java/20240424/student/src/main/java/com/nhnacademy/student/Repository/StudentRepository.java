package com.nhnacademy.student.Repository;

import com.nhnacademy.student.Student;

import java.util.List;

public interface StudentRepository {

    void save(Student student);

    void update(Student student);

    void deleteById(String id);

    Student getStudentById(String id);

    List<Student> getStudents();

    boolean existsById(String id);
}
