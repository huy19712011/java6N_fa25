package org.example.java6n_fa25.repository;

import org.example.java6n_fa25.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
