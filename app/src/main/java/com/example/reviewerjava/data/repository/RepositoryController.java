package com.example.reviewerjava.data.repository;

import com.example.reviewerjava.data.mock.MockBase;

public class RepositoryController {
    static Repository repository;

    public static Repository getRepository() {
        if (repository == null){
            repository = new MockBase();
        }
        return repository;
    }
}
