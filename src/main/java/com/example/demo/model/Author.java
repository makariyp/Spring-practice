package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "author")
@NoArgsConstructor
public class Author { // todo Хорошо бы добавить валидацию полей, например, вряд ли нам подойдет имя null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String biography;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;


}
