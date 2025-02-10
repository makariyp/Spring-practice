package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Author name cannot be blank")
    @Size(max = 100, message = "Author name must be at most 100 characters")
    private String name;

    @Size(max = 1000, message = "Biography must be at most 1000 characters")
    private String biography;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}