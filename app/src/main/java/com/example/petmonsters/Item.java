package com.example.petmonsters;
import android.graphics.Bitmap;

public class Item {
    private int type;
    private String name;
    private Bitmap sprite;

    Item(int type, String name, Bitmap sprite){
        this.type = type;
        this.name = name;
        this.sprite = sprite;
    }

    public int getType() {
        return type;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }
}
