package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.di.ServiceLocator;

import java.util.List;

public class RoleChangerViewModel extends ViewModel {
    public LiveData<List<UserEntity>>getUsers(){
        ServiceLocator.getInstance().getReviewerApi().getUserList();
        return ServiceLocator.getInstance().getUserRepository().getUsersExcept(
                CurrentUser.getInstance().getUser().getName()
        );
    }

    public void changeUserRole(String userName, String newRole){
        ServiceLocator.getInstance().getReviewerApi().changeRole(userName, newRole);
//        RepositoryController.changeUserRole(user, newRole);
    }

    public void updateUserList(){
        ServiceLocator.getInstance().getReviewerApi().getUserList();
//        RepositoryController.updateUserList();
    }
    public void ban(UserEntity user) {
        ServiceLocator.getInstance().getUserRepository().ban(user);
//        RepositoryController.ban(user);
    }
}
