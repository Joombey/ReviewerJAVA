package com.example.reviewerjava.data.model;

import android.view.View;

import com.example.reviewerjava.data.room.models.PermissionEntity;
import com.example.reviewerjava.data.room.models.UserEntity;

public class Permission {
    public Permission(){}
    Permission(Builder builder){
        role = builder.role;
        reviewBlockAccess = builder.reviewBlockAccess;
        roleChangerAccess = builder.roleChangerAccess;
        reviewMakerAccess = builder.reviewMakerAccess;
        profileAccess = builder.profileAccess;
    }

    public static final int VISIBLE = View.VISIBLE;
    public static final int INVISIBLE = View.GONE;

    public static final boolean ACCESS = true;
    public static final boolean DENY = false;

    public transient String role;
    public transient boolean reviewMakerAccess;
    public transient boolean profileAccess;
    public transient boolean reviewBlockAccess;
    public transient boolean roleChangerAccess;

    public static class Builder{
        String role;
        boolean reviewMakerAccess;
        boolean profileAccess;
        boolean reviewBlockAccess;
        boolean roleChangerAccess;
        public Builder(){
            role = "unauthorized";
            reviewBlockAccess = Permission.DENY;
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
        return new PermissionEntity(
                role,
                reviewMakerAccess,
                profileAccess,
                reviewBlockAccess,
                roleChangerAccess
        );
    }
}
