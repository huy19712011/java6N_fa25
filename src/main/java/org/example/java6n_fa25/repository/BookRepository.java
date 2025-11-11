package org.example.java6n_fa25.repository;

import org.example.java6n_fa25.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
