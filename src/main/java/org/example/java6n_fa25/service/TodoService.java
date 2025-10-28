package org.example.java6n_fa25.service;

import org.example.java6n_fa25.entity.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();

    Todo findById(long id);

    Todo add(Todo todo);

    Todo update(Todo todo, long id);

    Todo delete(long id);
}
