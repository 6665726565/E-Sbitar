package com.example.sbitar.Model;

public class Users {
    private String uid, name , email , password  ;

    public Users() {
    }

    public Users(String uId , String name, String email, String password, String profileImg, String fields, boolean isProfess) {
        this.uid = uId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


}
