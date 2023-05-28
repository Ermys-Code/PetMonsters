package com.example.petmonsters;

import android.app.Application;
import android.content.Context;
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
    private User currentFriend;
    private Pet currentFriendPet;
    private ArrayList<Item> store = new ArrayList<Item>();

    private static PetMonstersApplication instance;

    public PetMonstersApplication() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

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

    public void setStore(ArrayList<Item> store) {
        this.store = store;
    }

    public ArrayList<Item> getStore() {
        return store;
    }

    public User getCurrentFriend() {
        return currentFriend;
    }

    public void setCurrentFriend(User currentFriend) {
        this.currentFriend = currentFriend;
    }

    public Pet getCurrentFriendPet() {
        return currentFriendPet;
    }

    public void setCurrentFriendPet(Pet currentFriendPet) {
        this.currentFriendPet = currentFriendPet;
    }
}