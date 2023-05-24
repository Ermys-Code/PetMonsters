package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private ImageView play, store, friends, board; // Buttons
    private ImageView pet, shoes, accessory, hat; //sprites

    private View.OnClickListener playListener;
    private View.OnClickListener storeListener;
    private View.OnClickListener friendsListener;
    private View.OnClickListener boardListener;

    private TextView name;
    private TextView money;

    private BoardFragment boardFragment;
    private StoreFragment storeFragment;
    private FragmentTransaction fragmentTransaction;

    private PetMonstersApplication app = (PetMonstersApplication) getApplicationContext();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        assignFragments();
        assignViewFields();
        createListeners();
        assignListeners();

        showPet();
    }

    private void assignFragments(){
        boardFragment = new BoardFragment();
        storeFragment = new StoreFragment();
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
        play = findViewById(R.id.play);
        store = findViewById(R.id.store);
        friends = findViewById(R.id.friends);
        board = findViewById(R.id.board);
        pet = findViewById(R.id.pet);
        accessory = findViewById(R.id.accessory);
        hat = findViewById(R.id.hat);
        shoes = findViewById(R.id.shoes);
        name = findViewById(R.id.name);
        money = findViewById(R.id.money);

    }

    private void showPet(){
        name.setText(app.getPet().getName());

        money.setText(Integer.toString(app.getUser().getMoney()));

        pet.setImageBitmap(app.getPet().getSprite());

        if(app.getPet().getAccesory() != null){
            accessory.setImageBitmap(app.getPet().getAccesory().getSprite());
        }

        if(app.getPet().getHat() != null){
            hat.setImageBitmap(app.getPet().getHat().getSprite());
        }

        if(app.getPet().getHat() != null){
            shoes.setImageBitmap(app.getPet().getShoes().getSprite());
        }
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