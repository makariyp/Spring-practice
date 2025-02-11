package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.AuthorModel;

public interface AuthorService {

    AuthorModel createAuthor(Author author);

    AuthorModel getAuthor(Long id);

    AuthorModel updateAuthor(Long id, Author author);

    Void deleteAuthor(Long id);
}
