package com.example.reviewerjava.data.authordto;

import com.example.reviewerjava.data.model.Author;

public class AuthorDTO {
    private String name;
    private String city;

    public AuthorDTO(Author author) {
        this.name = author.getName();
        this.city = author.getCity();
    }
}
