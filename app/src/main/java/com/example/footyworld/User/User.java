package com.example.footyworld.User;


public class User {

    public String userId;
    public String userName;

    public String password;

    public User(){
    };

    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
