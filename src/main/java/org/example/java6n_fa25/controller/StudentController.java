package org.example.java6n_fa25.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java6n_fa25.entity.Student;
import org.example.java6n_fa25.entity.Todo;
import org.example.java6n_fa25.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllTodos() {

        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") long id) {

        Student student = studentService.findById(id);
        //return ResponseEntity.ok(todo);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addTodo(@Valid @RequestBody Student student) {

        Student savedStudent = studentService.add(student);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);

    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateTodo(@Valid @RequestBody Student student, @PathVariable("id") long id) {

        Student updatedStudent = studentService.update(student, id);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteTodo(@PathVariable("id") long id) {

        Student deletedStudent = studentService.delete(id);
        return new ResponseEntity<>(deletedStudent, HttpStatus.OK);

    }

}
