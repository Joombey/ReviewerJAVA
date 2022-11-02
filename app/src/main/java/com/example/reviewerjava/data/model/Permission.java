package com.example.reviewerjava.data.model;

import android.view.View;

import com.example.reviewerjava.data.room.models.PermissionEntity;

public class Permission {
    public Permission(){}
    Permission(Builder builder){
        reviewBlockAccess = builder.reviewBlockAccess;
        userBanAccess = builder.userBanAccess;
        roleChangerAccess = builder.roleChangerAccess;
        reviewMakerAccess = builder.reviewMakerAccess;
    }

    public static final int VISIBLE = View.VISIBLE;
    public static final int INVISIBLE = View.GONE;

    public static final boolean ACCESS = true;
    public static final boolean DENY = false;

    public boolean reviewMakerAccess;
    public boolean profileAccess;
    public boolean reviewBlockAccess;
    public boolean userBanAccess;
    public int roleChangerAccess;

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

    public int getRoleChangerAccess() {
        return roleChangerAccess;
    }

    public static class Builder{
        boolean reviewMakerAccess;
        boolean profileAccess;
        boolean reviewBlockAccess;
        boolean userBanAccess;
        int roleChangerAccess;
        public Builder(){
            reviewBlockAccess = Permission.DENY;
            userBanAccess = Permission.DENY;
            reviewMakerAccess = Permission.DENY;
            profileAccess = Permission.DENY;
            roleChangerAccess = Permission.VISIBLE;
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

        public Builder roleChangerAccess(int visibility){
            this.roleChangerAccess = visibility;
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
        permission.profileAccess = profileAccess;
        permission.role = "";
        permission.reviewBlockAccess = reviewBlockAccess;
        permission.reviewMakerAccess = reviewMakerAccess;
        permission.roleChangerAccess = roleChangerAccess;
        permission.userBanAccess = userBanAccess;
        return permission;
    }
}
