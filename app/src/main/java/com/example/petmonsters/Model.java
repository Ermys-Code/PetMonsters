package com.example.petmonsters;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;


public class Model {
    private String name;
    private ArrayList<String> sprites = new ArrayList<>();
    private ArrayList<String> colors;
    private ArrayList<String> tags;

    Model(String name, ArrayList<String> colors, ArrayList<String> tags){
        this.name = name;

        switch (name){
            case Constants.pet_name_e:
                sprites.add(Constants.pet_sprite_e_0);
                sprites.add(Constants.pet_sprite_e_1);
                sprites.add(Constants.pet_sprite_e_2);
                sprites.add(Constants.pet_sprite_e_3);
                break;
            case Constants.pet_name_bea:
                sprites.add(Constants.pet_sprite_bea_0);
                sprites.add(Constants.pet_sprite_bea_1);
                sprites.add(Constants.pet_sprite_bea_2);
                sprites.add(Constants.pet_sprite_bea_3);
                break;
            case Constants.pet_name_bob:
                sprites.add(Constants.pet_sprite_bob_0);
                sprites.add(Constants.pet_sprite_bob_1);
                sprites.add(Constants.pet_sprite_bob_2);
                sprites.add(Constants.pet_sprite_bob_3);
                break;
            case Constants.pet_name_carlos:
                sprites.add(Constants.pet_sprite_carlos_0);
                sprites.add(Constants.pet_sprite_carlos_1);
                sprites.add(Constants.pet_sprite_carlos_2);
                sprites.add(Constants.pet_sprite_carlos_3);
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