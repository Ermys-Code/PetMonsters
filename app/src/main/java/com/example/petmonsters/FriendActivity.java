package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendActivity extends AppCompatActivity {

    private ImageView friends_iv, board_iv; // Buttons
    private ImageView pet_iv, shoes_iv, hat_iv; //sprites

    private View.OnClickListener friendsListener;
    private View.OnClickListener boardListener;

    private TextView name_tv, petname_tv;

    private BoardFragment boardFragment;
    private FragmentTransaction fragmentTransaction;

    private PetMonstersApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getAppContext();

        assignFragments();
        assignViewFields();
        createListeners();
        assignListeners();

        showPet();
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getApplicationContext();
    }

    private void assignFragments(){
        boardFragment = new BoardFragment();
    }

    private void createListeners() {

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
        friends_iv.setOnClickListener(friendsListener);
        board_iv.setOnClickListener(boardListener);
    }

    private void assignViewFields(){
        friends_iv = findViewById(R.id.friends_iv);
        board_iv = findViewById(R.id.board_iv);
        pet_iv = findViewById(R.id.pet_iv);
        hat_iv = findViewById(R.id.hat_iv);
        shoes_iv = findViewById(R.id.shoes_iv);
        name_tv = findViewById(R.id.name_tv);
        petname_tv = findViewById(R.id.petname_tv);
    }

    public void showPet(){
        name_tv.setText(app.getCurrentFriend().getUsername());
        petname_tv.setText(app.getCurrentFriendPet().getName());

        Resources res = app.getResources();
        int drawableId = res.getIdentifier(app.getCurrentFriendPet().getSprite(), "drawable", getApplicationContext().getPackageName());
        pet_iv.setImageDrawable(getResources().getDrawable(drawableId));

        if(!app.getCurrentFriendPet().getHat().equals("")){
            int hatId = res.getIdentifier(app.getCurrentFriendPet().getHat(), "drawable", getApplicationContext().getPackageName());
            hat_iv.setImageDrawable(getResources().getDrawable(hatId));
        }

        if(!app.getPet().getShoes().equals("")){
            int shoesId = res.getIdentifier(app.getCurrentFriendPet().getShoes(), "drawable", getApplicationContext().getPackageName());
            shoes_iv.setImageDrawable(getResources().getDrawable(shoesId));
        }
    }

    private void visitFriends(){
        Intent intent = new Intent(FriendActivity.this, SocialActivity.class);
        startActivity(intent);
    }

    private void useBoard(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (boardFragment.isAdded()) { fragmentTransaction.remove(boardFragment);}
        else{fragmentTransaction.add(R.id.fragment_container, boardFragment);
            fragmentTransaction.show(boardFragment);}
        fragmentTransaction.commit();

    }
}