package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.StudentAlreadyExistsException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    Map<String, Student> studentMap = new HashMap<>();

    public StudentRepositoryImpl() {
        studentMap.put("user", Student.create("user", "1234", "user", "a@a", 100, "good"));
    }
    @Override
    public boolean exists(String id) {
        return studentMap.containsKey(id);
    }

    @Override
    public Student register(String id, String password, String name, String email, int score, String comment) {
        if(exists(id)) {
            throw new StudentAlreadyExistsException("Student with id " + id + " already exists");
        }
        Student newStudent = Student.create(id, password, name, email, score, comment);
        studentMap.put(newStudent.getId(), newStudent);
        return newStudent;
    }

    @Override
    public Student modify(String id, String password, String name, String email, int score, String comment) {
        Student student = getStudent(id);
        student.setPassword(password);
        student.setName(name);
        student.setEmail(email);
        student.setScore(score);
        student.setComment(comment);
        studentMap.put(id, student);
        return student;
    }

    @Override
    public Student getStudent(String id) {
        if(!exists(id)) {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
        return studentMap.get(id);
    }

    public boolean matches(String id, String password) {
        return Optional.ofNullable(getStudent(id))
                .map(Student -> Student.getPassword().equals(password))
                .orElse(false);
    }
}
