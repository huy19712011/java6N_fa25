package org.example.java6n_fa25.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java6n_fa25.dto.TodoDto;
import org.example.java6n_fa25.entity.Todo;
import org.example.java6n_fa25.service.TodoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {

        List<TodoDto> todos = todoService.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") long id) {

        TodoDto todoDto = todoService.findById(id);
        //return ResponseEntity.ok(todo);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@Valid @RequestBody TodoDto todoDto) {

        Todo savedTodo = todoService.add(todoDto);

        return new ResponseEntity<>(todoDto, HttpStatus.CREATED);

    }

    @PutMapping("{id}")
    public ResponseEntity<Todo> updateTodo(@Valid @RequestBody TodoDto todo, @PathVariable("id") long id) {

        Todo updatedTodo = todoService.update(todo, id);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("{title}")
    public ResponseEntity<String> deleteTodo(@PathVariable("title") String title) {

        todoService.delete(title);
        return new ResponseEntity<>("Successfully deleted todo with title: " + title, HttpStatus.OK);

    }
}
