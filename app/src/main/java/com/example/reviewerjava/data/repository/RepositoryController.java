package com.example.reviewerjava.data.repository;

import com.example.reviewerjava.data.mock.MockBase;
import com.example.reviewerjava.data.model.Author;

public class RepositoryController {
    static Repository repository;
    static RegisterRepository registerRepository;

    public static Repository getRepository() {
        if (repository == null){
            repository = new MockBase();
        }
        return repository;
    }

    public static RegisterRepository getRegisterRepository(){
        if(registerRepository == null){
            registerRepository = new MockBase();
        }
        return registerRepository;
    }
}
