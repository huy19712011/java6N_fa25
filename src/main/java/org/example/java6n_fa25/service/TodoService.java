package org.example.java6n_fa25.service;

import org.example.java6n_fa25.dto.TodoDto;
import org.example.java6n_fa25.entity.Todo;

import java.util.List;

public interface TodoService {

    /* without DTO
    List<Todo> findAll();

    Todo findById(long id);

    Todo add(Todo todo);

    Todo update(Todo todo, long id);

    Todo delete(long id);
    */

    // TodoResponse
    List<TodoDto> findAll();

    // TodoResponse
    TodoDto findById(long id);

    // TodoRequest
    Todo add(TodoDto todo);

    // TodoRequest
    Todo update(TodoDto todo, long id);

    // TodoRequest: unique title
    void delete(String title);

}
