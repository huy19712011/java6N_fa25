package org.example.java6n_fa25.service;

import org.example.java6n_fa25.dto.BookRequest;
import org.example.java6n_fa25.dto.BookResponse;

import java.util.List;

public interface BookService {

    List<BookResponse> findAll();
    BookResponse findById(long id);

    BookResponse add(BookRequest book);
    BookResponse update(BookRequest book, long id);

    //BookResponse delete(long id);
    void delete(long id);

}
