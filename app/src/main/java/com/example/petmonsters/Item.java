package com.example.petmonsters;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class Item {
    private int type;
    private String name;
    private String sprite;
    private ArrayList<String> tags;
    private int value;

    Item(int type, String name, String sprite, ArrayList<String> tags, int value){
        this.type = type;
        this.name = name;
        this.sprite = sprite;
        this.tags = tags;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public String getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public int getValue() {
        return value;
    }
}
