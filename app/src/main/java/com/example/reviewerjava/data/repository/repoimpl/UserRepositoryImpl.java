package com.example.reviewerjava.data.repository.repoimpl;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.repository.repos.UserRepository;
import com.example.reviewerjava.data.room.ReviewerRoomDb;
import com.example.reviewerjava.data.room.daos.UserAndPermissionDao;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final UserAndPermissionDao dao;

    public UserRepositoryImpl(UserAndPermissionDao dao){
        this.dao = dao;
    }

    @Override
    public void updateUser(UserEntity user) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()-> {
            dao.updateUserState(user);
        });
    }

    @Override
    public UserEntity getUserByName(String userName) {
        return dao.getUserByName(userName);
    }

    @Override
    public LiveData<List<UserEntity>> getUsersExcept(String userName) {
        return dao.getUsers(userName);
    }

    @Override
    public void ban(UserEntity user) {
        dao.deleteUser(user);
    }

    @Override
    public void addNewUser(UserEntity newUser) {
        dao.insertUser(newUser);
    }

    @Override
    public boolean userExists(String name) {
        if(dao.getUser(name) == null) return false;
        else return true;
    }

    @Override
    public void addUserAndPermission(UserAndPermission userAndPermission) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.insertUserAndPermission(userAndPermission);
        });
    }

    @Override
    public UserAndPermission getUserAndPermission(String name) {
        return dao.getUser(name);
    }

    @Override
    public void addUserList(List<UserAndPermission> userList) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            for(int i = 0; i < userList.size(); i++) {
                dao.insertUserAndPermission(userList.get(i));
            }
        });
    }
}
