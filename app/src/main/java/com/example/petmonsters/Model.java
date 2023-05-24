package com.example.petmonsters;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;


public class Model {
    private String name;
    private ArrayList<Bitmap> sprites = new ArrayList<Bitmap>(); // todos los diferentes modelos con su color
    private ArrayList<Integer> colors = new ArrayList<Integer>(); //color para el boton

    Model(String name, ArrayList<Bitmap> sprites, ArrayList<Integer> colors ){
        this.sprites = sprites;
        this.colors = colors;
        this.name = name;
    }

    public ArrayList<Bitmap> getSprites() {
        return sprites;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }
}
