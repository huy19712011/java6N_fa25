package org.example.java6n_fa25.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.java6n_fa25.entity.Student;
import org.example.java6n_fa25.entity.Todo;
import org.example.java6n_fa25.exception.CustomResourceNotFoundException;
import org.example.java6n_fa25.repository.StudentRepository;
import org.example.java6n_fa25.repository.TodoRepository;
import org.example.java6n_fa25.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {

        return studentRepository.findAll();
    }

    @Override
    public Student findById(long id) {

        return studentRepository
                .findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Student not found for this id: " + id));
    }

    @Override
    public Student add(Student student) {

        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    @Override
    public Student update(Student student, long id) {

        Student retrievedStudent = findById(id);

        if (retrievedStudent == null) return null;

        retrievedStudent.setName(student.getName());
        retrievedStudent.setEmail(student.getEmail());
        retrievedStudent.setPhone(student.getPhone());
        return studentRepository.save(retrievedStudent);

    }

    @Override
    public Student delete(long id) {

        Student deletedStudent = findById(id);

        if (deletedStudent != null) {
            studentRepository.deleteById(id);
        }

        return deletedStudent;

    }
}
