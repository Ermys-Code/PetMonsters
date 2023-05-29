package com.example.petmonsters;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;


public class Model {
    private String name;
    private ArrayList<String> sprites = new ArrayList<>();
    private ArrayList<String> colors = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();

    Model(String name, ArrayList<String> colors, ArrayList<String> tags){
        this.name = name;

        switch (name){
            case "e":
                sprites.add("e_0");
                sprites.add("e_1");
                sprites.add("e_2");
                sprites.add("e_3");
                break;
            case "bea":
                sprites.add("bea_0");
                sprites.add("bea_1");
                sprites.add("bea_2");
                sprites.add("bea_3");
                break;
            case "bob":
                sprites.add("bob_0");
                sprites.add("bob_1");
                sprites.add("bob_2");
                sprites.add("bob_3");
                break;
            case "carlos":
                sprites.add("carlos_0");
                sprites.add("carlos_1");
                sprites.add("carlos_2");
                sprites.add("carlos_3");
                break;
        }

        this.colors = colors;
        this.tags = tags;
    }

    public ArrayList<String> getSprites() {
        return sprites;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
