package com.example.demo.service;

import com.example.demo.exception.NoSuchAuthorException;
import com.example.demo.model.Author;
import com.example.demo.model.AuthorModel;
import com.example.demo.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional
    public AuthorModel createAuthor(Author author) {
        return AuthorModel.from(authorRepository.save(author));
    }


    @Transactional(readOnly = true)
    public AuthorModel getAuthor(Long id) {
        return AuthorModel.from(authorRepository.findById(id).orElseThrow(() -> new NoSuchAuthorException("Author not found")));
    }

    @Transactional
    public AuthorModel updateAuthor(Long id, Author author) {
        Author oldAuthor = authorRepository.findById(id).orElseThrow(() -> new NoSuchAuthorException("Author not found"));

        oldAuthor.setBiography(author.getBiography());
        oldAuthor.setName(author.getName());
        return AuthorModel.from(authorRepository.save(oldAuthor));
    }

    @Transactional
    public Void deleteAuthor(Long id) {
        authorRepository.findById(id).orElseThrow(() -> new NoSuchAuthorException("Author not found"));
        authorRepository.deleteById(id);


        return null;
    }
}
