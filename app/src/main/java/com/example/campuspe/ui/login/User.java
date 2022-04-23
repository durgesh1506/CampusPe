package com.example.campuspe.ui.login;

public class User {

    public String username;
    public String phone;
    public String regNo;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String phone,String regNo) {
        this.username = username;
        this.phone = phone;
        this.regNo = regNo;

    }

}
