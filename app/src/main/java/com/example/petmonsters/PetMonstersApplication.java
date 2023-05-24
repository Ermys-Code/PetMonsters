package com.example.petmonsters;

import android.app.Application;

import java.util.ArrayList;
import java.util.Date;

public class PetMonstersApplication extends Application {

    private User user;
    private Pet pet;
    private Board board;
    private ArrayList<Item> store = new ArrayList<Item>();

    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return this.user;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }
}