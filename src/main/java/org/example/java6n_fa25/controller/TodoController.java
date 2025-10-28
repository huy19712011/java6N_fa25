package org.example.java6n_fa25.controller;

import lombok.RequiredArgsConstructor;
import org.example.java6n_fa25.entity.Todo;
import org.example.java6n_fa25.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {

        List<Todo> todos = todoService.findAll();
        //return ResponseEntity.ok(todos);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") long id) {

        Todo todo = todoService.findById(id);
        //return ResponseEntity.ok(todo);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {

        Todo savedTodo = todoService.add(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable("id") long id) {

        Todo updatedTodo = todoService.update(todo, id);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable("id") long id) {

        Todo deletedTodo = todoService.delete(id);
        return new ResponseEntity<>(deletedTodo, HttpStatus.OK);

        // if return void
        //ResponseEntity.noContent().build();
        // if not found
        //ResponseEntity.notFound().build();
    }
}
