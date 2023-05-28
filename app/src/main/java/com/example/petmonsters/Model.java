package com.example.petmonsters;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;


public class Model {
    private String name;
    private ArrayList<Integer> sprites = new ArrayList<>();
    private ArrayList<String> colors = new ArrayList<>();

    Model(String name, ArrayList<String> colors ){
        this.name = name;

        switch (name){
            case "e":
                sprites.add(R.drawable.e_0);
                sprites.add(R.drawable.e_1);
                sprites.add(R.drawable.e_2);
                sprites.add(R.drawable.e_3);
                break;
            case "bea":
                sprites.add(R.drawable.bea_0);
                sprites.add(R.drawable.bea_1);
                sprites.add(R.drawable.bea_2);
                sprites.add(R.drawable.bea_3);
                break;
            case "bob":
                sprites.add(R.drawable.bob_0);
                sprites.add(R.drawable.bob_1);
                sprites.add(R.drawable.bob_2);
                sprites.add(R.drawable.bob_3);
                break;
            case "carlos":
                sprites.add(R.drawable.carlos_0);
                sprites.add(R.drawable.carlos_1);
                sprites.add(R.drawable.carlos_2);
                sprites.add(R.drawable.carlos_3);
                break;
        }

        this.colors = colors;
    }

    public ArrayList<Integer> getSprites() {
        return sprites;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }
}
