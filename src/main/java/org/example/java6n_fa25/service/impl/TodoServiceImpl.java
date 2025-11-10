package org.example.java6n_fa25.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.java6n_fa25.dto.TodoDto;
import org.example.java6n_fa25.entity.Todo;
import org.example.java6n_fa25.exception.CustomResourceNotFoundException;
import org.example.java6n_fa25.repository.TodoRepository;
import org.example.java6n_fa25.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<TodoDto> findAll() {

        return todoRepository.findAll()
                .stream()
                .map(todo -> {
                    return new TodoDto(
                            todo.getTitle(),
                            todo.getDescription(),
                            todo.isCompleted()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto findById(long id) {

        return todoRepository
                .findById(id)
                .map(todo -> new TodoDto(todo.getTitle(), todo.getDescription(), todo.isCompleted()))
                .orElseThrow(() -> new CustomResourceNotFoundException("Todo not found for this id: " + id));
    }

    @Override
    public Todo add(TodoDto todoDto) {

        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        return todoRepository.save(todo);
    }

    @Override
    public Todo update(TodoDto todoDto, long id) {

        return todoRepository
                .findById(id)
                .map(existing -> {
                    if (todoDto.getTitle() != null) existing.setTitle(todoDto.getTitle());
                    if (todoDto.getDescription() != null) existing.setDescription(todoDto.getDescription());
                    existing.setCompleted(todoDto.isCompleted());
                    return todoRepository.save(existing);
                })
                .orElseThrow(() -> new CustomResourceNotFoundException("Todo not found with id: " + id));

    }

    @Override
    public void delete(String title) {

        Todo todo = todoRepository
                .findByTitle(title)
                .orElseThrow(() -> new CustomResourceNotFoundException("Todo not found with title: " + title));

        todoRepository.delete(todo);
    }

    /* without DTO
    @Override
    public List<Todo> findAll() {

        return todoRepository.findAll();
    }

    @Override
    public Todo findById(long id) {

        return todoRepository
                .findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Todo not found for this id: " + id));
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

        //return todoRepository.findById(id)
        //        .map(todo -> {
        //            todoRepository.deleteById(id);
        //            return todo;
        //        })
        //        .orElseThrow(() -> new CustomResourceNotFoundException("Todo not found for this id: " + id));


    }
    */



}
