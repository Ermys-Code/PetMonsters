package com.example.petmonsters;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;


public class Model {
    private String name;
    private Bitmap sprite;
    private ArrayList<Integer> colors = new ArrayList<Integer>();

    Model(String name, Bitmap sprite, ArrayList<Integer> colors ){
        this.sprite = sprite;
        this.colors = colors;
        this.name = name;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }
}
