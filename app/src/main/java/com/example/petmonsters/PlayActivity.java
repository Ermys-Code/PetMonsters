package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PlayActivity extends AppCompatActivity {

    //Array de ImageView
    //Array de tablero

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        assignViewFields();
        createListeners();
        assignListeners();

        createTiles();
        fillGrid();
    }

    //TODO:VIEW STUFF
    private void assignViewFields() {
        //coger tablero layout
        //for child int tableroLayout add hijo (ImageView) to array de ImageView

        //index = 0 (para llevar el indice en la array de ImageView)
        //for range 7
            //crear array temporal
            //for range 7
                //new Tile  (array de Imageview(index))
                //añadir tile a array temporal
                //indice ++
            //añadir array temporal a tablero
    }

    private void createListeners() {
        //for tile in tablero
        //OnClick listener for attention
        //OnTouch listener from stackOverflow Adapted and pray
    }

    private void assignListeners(){
        //assign on click and touch listeners to viewImages
    }


    //TODO:TILE CREATION
    public void createTiles(){
        //create object with viewImage
        //call rolTypes
        //call addTileToArrays
        //call saveTileSurroundings
    }

    public void rolTypes(){
        //random type for tile
        //set type to tile
    }

    public void addTileToArrays(){
        //find witch arrays corresponds and save
    }

    public void saveTileSurroundings(){
        //save T,B,R,L,TR,TL,BR,BL
    }

    //TODO:EXECUTION
    public void fillGrid(){
        //show on each tile its type img drawable
        //call calculatePosiblePath

        //if false
        //wait for seconds o lo que sea y mostrar toast no hay combinaciones
        //call rollTypes and fillGrid again
    }

    public void calculatePossiblePath(){
        //see if there is least one possible path.
    }

    //link a pista de como hacerlo
    //https://stackoverflow.com/questions/12980156/detect-touch-event-on-a-view-when-dragged-over-from-other-view

}