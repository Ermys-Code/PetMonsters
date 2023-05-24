package com.example.petmonsters;
import android.graphics.Bitmap;

public class Pet {
    private String owner;
    private Bitmap sprite;
    private int color; // color para el estudio de big data
    private Item shoes;
    private Item hat;
    private Item accesory;
    private String name;


    Pet(String owner, Bitmap sprite, int color, Item shoes, Item hat, Item accesory, String name){
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

    public Bitmap getSprite() {
        return sprite;
    }

    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Item getHat() {
        return hat;
    }

    public void setHat(Item hat) {
        this.hat = hat;
    }

    public Item getAccesory() {
        return accesory;
    }

    public void setAccesory(Item accesory) {
        this.accesory = accesory;
    }

    public Item getShoes() {
        return shoes;
    }

    public void setShoes(Item shoes) {
        this.shoes = shoes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


