package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorModel {
    private Long id;
    private String name;
    private String biography;

    public static AuthorModel from(Author author){
        AuthorModel model = new AuthorModel();
        model.setId(author.getId());
        model.setName(author.getName());
        model.setBiography(author.getBiography());
        return model;
    }
}