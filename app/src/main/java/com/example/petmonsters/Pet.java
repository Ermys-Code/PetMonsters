package com.example.petmonsters;
import android.graphics.Bitmap;

public class Pet {
    private String owner;
    private int sprite;
    private String color; // color para el estudio de big data
    private int shoes;
    private int hat;
    private String name;


    Pet(String owner, int sprite, String color, int shoes, int hat, String name){
        this.owner = owner;
        this.sprite = sprite;
        this.color = color;
        this.shoes = shoes;
        this.hat = hat;
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getSprite() {
        return sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHat() {
        return hat;
    }

    public void setHat(int hat) {
        this.hat = hat;
    }

    public int getShoes() {
        return shoes;
    }

    public void setShoes(int shoes) {
        this.shoes = shoes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


