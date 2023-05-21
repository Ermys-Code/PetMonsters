package com.example.petmonsters;

import android.app.Application;

import java.util.Date;

public class PetMonstersApplication extends Application {

    private User user;

    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return this.user;
    }
}