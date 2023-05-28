package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private ImageView play_iv, store_iv, friends_iv, board_iv; // Buttons
    private ImageView pet_iv, shoes_iv, accessory_iv, hat_iv; //sprites

    private View.OnClickListener playListener;
    private View.OnClickListener storeListener;
    private View.OnClickListener friendsListener;
    private View.OnClickListener boardListener;

    private TextView name_tv;
    private TextView money_tv;

    private BoardFragment boardFragment;
    private StoreFragment storeFragment;
    private FragmentTransaction fragmentTransaction;

    private PetMonstersApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getAppContext();

        assignFragments();
        assignViewFields();
        createListeners();
        assignListeners();

        System.out.println(app.getUser().getName());

        //showPet();
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getApplicationContext();
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
        play_iv.setOnClickListener(playListener);
        store_iv.setOnClickListener(storeListener);
        friends_iv.setOnClickListener(friendsListener);
        board_iv.setOnClickListener(boardListener);
    }

    private void assignViewFields(){
        play_iv = findViewById(R.id.play_iv);
        store_iv = findViewById(R.id.store_iv);
        friends_iv = findViewById(R.id.friends_iv);
        board_iv = findViewById(R.id.board_iv);
        pet_iv = findViewById(R.id.pet_iv);
        accessory_iv = findViewById(R.id.accessory_iv);
        hat_iv = findViewById(R.id.hat_iv);
        shoes_iv = findViewById(R.id.shoes_iv);
        name_tv = findViewById(R.id.name_tv);
        money_tv = findViewById(R.id.money_tv);
    }

    private void showPet(){
        name_tv.setText(app.getPet().getName());

        money_tv.setText(Integer.toString(app.getUser().getMoney()));

        //pet_iv.setImageBitmap(app.getPet().getSprite());

        if(app.getPet().getAccesory() != null){
            //accessory_iv.setImageBitmap(app.getPet().getAccesory().getSprite());
        }

        if(app.getPet().getHat() != null){
           // hat_iv.setImageBitmap(app.getPet().getHat().getSprite());
        }

        if(app.getPet().getHat() != null){
            //shoes_iv.setImageBitmap(app.getPet().getShoes().getSprite());
        }
    }

    private void playGame(){


    }

    private void checkStore(){
       fragmentTransaction = getSupportFragmentManager().beginTransaction();
       if (boardFragment.isAdded()) { fragmentTransaction.remove(boardFragment);}
       if (storeFragment.isAdded()) { fragmentTransaction.remove(storeFragment);}
       else{fragmentTransaction.add(R.id.fragment_container, storeFragment);
           fragmentTransaction.show(storeFragment);}
        fragmentTransaction.commit();


    }

    private void visitFriends(){

    }

    private void useBoard(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (storeFragment.isAdded()) { fragmentTransaction.remove(storeFragment);}
        if (boardFragment.isAdded()) { fragmentTransaction.remove(boardFragment);}
        else{fragmentTransaction.add(R.id.fragment_container, boardFragment);
            fragmentTransaction.show(boardFragment);}
        fragmentTransaction.commit();

    }
}