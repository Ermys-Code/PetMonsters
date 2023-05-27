package com.example.petmonsters;
import android.graphics.Bitmap;

public class Pet {
    private String owner;
    private String sprite;
    private int color; // color para el estudio de big data
    private String shoes;
    private String hat;
    private String accesory;
    private String name;


    Pet(String owner, String sprite, int color, String shoes, String hat, String accesory, String name){
        this.owner = owner;
        this.sprite = sprite;
        this.color = color;
        this.shoes = shoes;
        this.hat = hat;
        this.accesory = accesory;
        this.name = name;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getHat() {
        return hat;
    }

    public void setHat(String hat) {
        this.hat = hat;
    }

    public String getAccesory() {
        return accesory;
    }

    public void setAccesory(String accesory) {
        this.accesory = accesory;
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
}


