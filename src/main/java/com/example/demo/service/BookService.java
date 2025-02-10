package com.example.demo.service;

import com.example.demo.exception.NoSuchBookException;
import com.example.demo.model.Book;
import com.example.demo.model.BookModel;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceImpl {

    private final BookRepository bookRepository;

    @Transactional
    public BookModel createBook(Book book) {

        return BookModel.from(bookRepository.save(book));

    }

    @Transactional
    public BookModel getBook(Long id) {
        return BookModel.from(bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException("Book not found")));
    }

    @Transactional
    public BookModel updateBook(Long id, Book book) {
        Book oldBook = bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException("Book not found"));


        oldBook.setTitle(book.getTitle());
        oldBook.setDescription(book.getDescription());
        oldBook.setAuthors(book.getAuthors());

        return BookModel.from(bookRepository.save(oldBook));
    }

    @Transactional
    public Void deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException("Book not found"));
        bookRepository.deleteById(id);
        return null;
    }
}
