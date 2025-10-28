package org.example.java6n_fa25.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.java6n_fa25.entity.Todo;
import org.example.java6n_fa25.repository.TodoRepository;
import org.example.java6n_fa25.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {

        return todoRepository.findAll();
    }

    @Override
    public Todo findById(long id) {

        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public Todo add(Todo todo) {

        Todo savedTodo = todoRepository.save(todo);
        return savedTodo;
    }

    @Override
    public Todo update(Todo todo, long id) {

        Todo retrievedTodo = findById(id);

        if (retrievedTodo == null) return null;

        retrievedTodo.setTitle(todo.getTitle());
        retrievedTodo.setDescription(todo.getDescription());
        retrievedTodo.setCompleted(todo.isCompleted());
        return todoRepository.save(retrievedTodo);

        //return todoRepository
        //        .findById(id)
        //        .map(existing -> {
        //            if (todo.getTitle() != null) existing.setTitle(todo.getTitle());
        //            if (todo.getDescription() != null) existing.setDescription(todo.getDescription());
        //            existing.setCompleted(todo.isCompleted());
        //            return todoRepository.save(existing);
        //        })
        //        .orElse(null);


    }

    @Override
    public Todo delete(long id) {

        Todo deletedTodo = findById(id);

        if (deletedTodo != null) {
            todoRepository.deleteById(id);
        }

        return deletedTodo;
    }
}
