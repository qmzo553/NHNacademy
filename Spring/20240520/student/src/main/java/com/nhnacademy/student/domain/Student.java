package com.nhnacademy.student.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String id;
    private String password;
    private String name;
    private String email;
    private int score;
    private String comment;

    public static Student create(String id, String password, String name, String email, int score, String comment) {
        return new Student(id, password, name, email, score, comment);
    }

    private Student(String id, String password, String name, String email, int score, String comment) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }

    private static final String MASK = "******";

    public static Student constructPasswordMaskedStudent(Student student) {
        Student newStudent = Student.create(student.getId(), MASK, student.getName(), student.getEmail(), student.getScore(), student.getComment());
        return newStudent;
    }
}
