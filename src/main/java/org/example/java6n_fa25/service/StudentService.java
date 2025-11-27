package org.example.java6n_fa25.service;

import org.example.java6n_fa25.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(long id);

    Student add(Student student);

    Student update(Student student, long id);

    Student delete(long id);
}
