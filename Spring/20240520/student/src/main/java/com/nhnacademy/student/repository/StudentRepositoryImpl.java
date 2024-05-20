package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    Map<String, Student> studentMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return studentMap.containsKey(id);
    }

    @Override
    public Student register(String id, String password, String name, String email, int score, String comment) {
        Student newStudent = Student.create(id, password, name, email, score, comment);
        studentMap.put(newStudent.getId(), newStudent);
        return newStudent;
    }

    @Override
    public Student getStudent(String id) {
        return studentMap.get(id);
    }

    public boolean matches(String id, String password) {
        return Optional.ofNullable(getStudent(id))
                .map(Student -> Student.getPassword().equals(password))
                .orElse(false);
    }
}
