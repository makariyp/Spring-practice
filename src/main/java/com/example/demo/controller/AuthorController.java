package com.example.demo.controller;

import com.example.demo.api.Endpoints;
import com.example.demo.model.Author;
import com.example.demo.model.AuthorModel;
import com.example.demo.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.AUTHOR)
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorModel> createAuthor(@RequestBody @Valid Author author) {
        return ResponseEntity.ok(authorService.createAuthor(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorModel> getAuthor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorModel> updateAuthor(@PathVariable("id") Long id, @RequestBody @Valid Author author) {
        return ResponseEntity.ok(authorService.updateAuthor(id, author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {

        return ResponseEntity.ok(authorService.deleteAuthor(id));
    }
}
