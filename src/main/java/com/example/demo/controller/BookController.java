package com.example.demo.controller;

import com.example.demo.api.Endpoints;
import com.example.demo.model.Book;
import com.example.demo.model.BookModel;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.BOOK)
public class BookController {
     private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookModel> createBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBook(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id,@RequestBody Book book ){
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id ){
        if (bookService.deleteBook(id)) { // todo тут лучше сделать через NoSuchBookException, чтобы соблюсти единообразие по аналогии с get и update методами
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
