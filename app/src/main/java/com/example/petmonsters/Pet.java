package com.example.petmonsters;
import android.graphics.Bitmap;

import java.util.ArrayList;

public class Pet {
    private String owner;
    private String sprite;
    private String color; // color para el estudio de big data
    private String shoes;
    private String hat;
    private String name;
    private ArrayList<String> tags;


    Pet(String owner, String sprite, String color, String shoes, String hat, String name, ArrayList<String> tags){
        this.owner = owner;
        this.sprite = sprite;
        this.color = color;
        this.shoes = shoes;
        this.hat = hat;
        this.name = name;
        this.tags = tags;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHat() {
        return hat;
    }

    public void setHat(String hat) {
        this.hat = hat;
    }

    public String getShoes() {
        return shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}


