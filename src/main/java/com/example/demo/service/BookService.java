package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BookModel;

public interface BookService {
    BookModel createBook(Book book);

    BookModel getBook(Long id);

    BookModel updateBook(Long id, Book book);

    Void deleteBook(Long id);
}
