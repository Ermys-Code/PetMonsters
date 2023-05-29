package com.example.petmonsters;

import android.widget.ImageView;

import java.util.ArrayList;

public class Tile {

    private ImageView tile;
    private ArrayList<Tile> nearTiles = new ArrayList<>(); // T, B, R, L, TR, TL, BR, BL
    private Boolean isClicked;
    private String currentType;

    public Tile(ImageView tile){
        this.tile = tile;
        this.isClicked = false;

    }

    public ArrayList<Tile> getNearTiles() {
        return nearTiles;
    }

    public void setNearTiles(ArrayList<Tile> nearTiles) {
        this.nearTiles = nearTiles;
    }

    public ImageView getTile() {
        return tile;
    }

    public void setClicked(Boolean clicked) {
        isClicked = clicked;
    }

    public Boolean getClicked() {
        return isClicked;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }
}
