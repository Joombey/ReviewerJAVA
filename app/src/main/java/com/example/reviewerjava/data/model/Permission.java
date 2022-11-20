package com.example.reviewerjava.data.model;

import android.view.View;

import com.example.reviewerjava.data.room.models.PermissionEntity;
import com.example.reviewerjava.data.room.models.UserEntity;

public class Permission {
    public Permission(){}
    Permission(Builder builder){
        role = builder.role;
        reviewBlockAccess = builder.reviewBlockAccess;
        userBanAccess = builder.userBanAccess;
        roleChangerAccess = builder.roleChangerAccess;
        reviewMakerAccess = builder.reviewMakerAccess;
        profileAccess = builder.profileAccess;
    }

    public static final int VISIBLE = View.VISIBLE;
    public static final int INVISIBLE = View.GONE;

    public static final boolean ACCESS = true;
    public static final boolean DENY = false;

    public String role;
    public boolean reviewMakerAccess;
    public boolean profileAccess;
    public boolean reviewBlockAccess;
    public boolean userBanAccess;
    public boolean roleChangerAccess;

    public boolean isReviewMakerAccess() {
        return reviewMakerAccess;
    }

    public boolean isProfileAccess() {
        return profileAccess;
    }

    public boolean isReviewBlockAccess() {
        return reviewBlockAccess;
    }

    public boolean isUserBanAccess() {
        return userBanAccess;
    }

    public boolean getRoleChangerAccess() {
        return roleChangerAccess;
    }

    public static class Builder{
        String role;
        boolean reviewMakerAccess;
        boolean profileAccess;
        boolean reviewBlockAccess;
        boolean userBanAccess;
        boolean roleChangerAccess;
        public Builder(){
            role = "unauthorized";
            reviewBlockAccess = Permission.DENY;
            userBanAccess = Permission.DENY;
            reviewMakerAccess = Permission.DENY;
            profileAccess = Permission.ACCESS;
            roleChangerAccess = Permission.DENY;
        }

        public Builder role(String role){
            this.role = role;
            return this;
        }
        public Builder reviewBlockAccess(boolean access){
            this.reviewBlockAccess = access;
            return this;
        }

        public Builder userBanAccess(boolean access){
            this.userBanAccess = access;
            return this;
        }

        public Builder reviewMakerAccess(boolean access){
            this.reviewMakerAccess = access;
            return this;
        }

        public Builder roleChangerAccess(boolean access){
            this.roleChangerAccess = access;
            return this;
        }

        public Builder profileAccess(boolean access){
            this.profileAccess = access;
            return this;
        }

        public Permission build(){
            return new Permission(this);
        }
    }

    public PermissionEntity getPermissionEntityInstance(){
        PermissionEntity permission = new PermissionEntity();
        permission.role = role;
        permission.profileAccess = profileAccess;
        permission.reviewBlockAccess = reviewBlockAccess;
        permission.reviewMakerAccess = reviewMakerAccess;
        permission.roleChangerAccess = roleChangerAccess;
        permission.userBanAccess = userBanAccess;
        return permission;
    }
}
