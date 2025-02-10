package com.example.demo.controller;

import com.example.demo.api.Endpoints;
import com.example.demo.model.Author;
import com.example.demo.model.AuthorModel;
import com.example.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.AUTHOR)
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorModel> createAuthor(@RequestBody Author author){
        return ResponseEntity.ok(authorService.createAuthor(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorModel> getAuthor(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorModel> updateAuthor(@PathVariable Long id,@RequestBody Author author ){
        return ResponseEntity.ok(authorService.updateAuthor(id, author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        if (authorService.deleteAuthor(id)) { // todo тут можно через NoSuchAuthorException, по аналогии с updateAuthor и getAuthor
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
