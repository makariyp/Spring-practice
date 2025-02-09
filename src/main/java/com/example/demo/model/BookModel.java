package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookModel {
    Long id;
    String title;
    String description;

    public static BookModel from(Book book){
        BookModel model = new BookModel();
        model.setId(book.getId());
        model.setTitle(book.getTitle());
        model.setDescription(book.getDescription());
        return model;
    }
}