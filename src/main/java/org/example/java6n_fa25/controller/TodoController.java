package org.example.java6n_fa25.controller;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<Todo>> getAllTodos() {

        List<Todo> todos = todoService.findAll();
        //return ResponseEntity.ok(todos); // utility
        //return new ResponseEntity<>(todos, HttpStatus.BAD_REQUEST); // we can return with different status codes for different scenarios

        // Additionally, we can set HTTP headers
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("Custom-Header", "custom value");
        //return new ResponseEntity<>(todos, headers, HttpStatus.OK);

        //
        //https://www.baeldung.com/spring-response-entity
        //BodyBuilder accepted();
        //BodyBuilder badRequest();
        //BodyBuilder created(java.net.URI location);
        //HeadersBuilder<?> noContent();
        //HeadersBuilder<?> notFound();
        //BodyBuilder ok();

        // return ResponseEntity.status(HttpStatus.OK).body(todos);
        //return ResponseEntity.badRequest().body(todos);
        //return ResponseEntity.ok().header("Custom-Header", "custom-value").body(todos);

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

        //URI locationUri = ServletUriComponentsBuilder
        //        .fromCurrentRequest()
        //        .path("/{id}")
        //        .buildAndExpand(savedTodo.getId())
        //        .toUri();
        //return ResponseEntity.created(locationUri).build();


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
