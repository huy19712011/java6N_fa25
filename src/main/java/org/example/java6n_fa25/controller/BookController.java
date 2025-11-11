package org.example.java6n_fa25.controller;

import lombok.RequiredArgsConstructor;
import org.example.java6n_fa25.dto.BookRequest;
import org.example.java6n_fa25.dto.BookResponse;
import org.example.java6n_fa25.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {

        //return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
        //return ResponseEntity.ok(bookService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") long id) {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> add(@RequestBody BookRequest bookRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.add(bookRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<BookResponse> update(@RequestBody BookRequest bookRequest, @PathVariable("id") long id) {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(bookRequest, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {

        bookService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
