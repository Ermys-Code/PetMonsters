package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity {

    private ImageView play, store, friends, board; // Buttons

    private ImageView pet; //model

    private View.OnClickListener playListener;
    private View.OnClickListener storeListener;
    private View.OnClickListener friendsListener;
    private View.OnClickListener boardListener;

    PetMonstersApplication app = (PetMonstersApplication) getApplicationContext();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        assignViewFields();
        createListeners();
        assignListeners();

        showPet();
    }

    private void createListeners() {
        playListener = new View.OnClickListener() {
            public void onClick(View view) {
                playGame();
            }
        };

        storeListener = new View.OnClickListener() {
            public void onClick(View view) {
                checkStore();
            }
        };

        friendsListener = new View.OnClickListener() {
            public void onClick(View view) {
                visitFriends();
            }
        };

        boardListener = new View.OnClickListener() {
            public void onClick(View view) {
                useBoard();
            }
        };
    }

    private void assignListeners(){
        play.setOnClickListener(playListener);
        store.setOnClickListener(storeListener);
        friends.setOnClickListener(friendsListener);
        board.setOnClickListener(boardListener);
    }

    private void assignViewFields(){
        play.findViewById(R.id.play);
        store.findViewById(R.id.store);
        friends.findViewById(R.id.friends);
        board.findViewById(R.id.board);
    }

    private void showPet(){
        pet.setImageBitmap(app.getPet().getSprite());
    }

    private void playGame(){

    }

    private void checkStore(){

    }

    private void visitFriends(){

    }

    private void useBoard(){

    }
}