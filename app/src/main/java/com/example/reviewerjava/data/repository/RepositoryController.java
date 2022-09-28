package com.example.reviewerjava.data.repository;

import com.example.reviewerjava.data.mock.MockBase;
import com.example.reviewerjava.data.repository.repos.AddReviewRepository;
import com.example.reviewerjava.data.repository.repos.RegisterRepository;
import com.example.reviewerjava.data.repository.repos.ReviewListRepository;

public class RepositoryController {
    static ReviewListRepository reviewListRepository;
    static RegisterRepository registerRepository;
    static AddReviewRepository addReviewRepository;

    public static ReviewListRepository getReviewListRepository() {
        if (reviewListRepository == null){
            reviewListRepository = new MockBase();
        }
        return reviewListRepository;
    }

    public static RegisterRepository getRegisterRepository(){
        if(registerRepository == null){
            registerRepository = new MockBase();
        }
        return registerRepository;
    }

    public static AddReviewRepository getAddReviewRepository(){
        if(addReviewRepository == null){
            addReviewRepository = new MockBase();
        }
        return addReviewRepository;
    }
}
