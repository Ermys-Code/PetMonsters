package com.example.petmonsters;

import android.app.Application;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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