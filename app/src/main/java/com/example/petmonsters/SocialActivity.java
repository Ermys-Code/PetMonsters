package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity {

    private LinearLayout layout, currentBox, currentHouse;
    private PetMonstersApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        getAppContext();

        assignViewFields();

        generateFriends();
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getApplicationContext();
    }

    private void assignViewFields(){
        layout = findViewById(R.id.social_layout);
    }

    private void generateFriends(){
        for(int i = 0; i < app.getUser().getFriends().toArray().length; i++) {
            String name = app.getUser().getFriends().get(i).getUsername();
            //TODO:recoger imagen del pet
            generateHouse(i, name);
        }

    }

    public void generateHouse(int index, String name) {

        if (index % 3 == 0) {
            currentBox = new LinearLayout(this);
            LinearLayout.LayoutParams boxParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            currentBox.setLayoutParams(boxParameters);
            boxParameters.setMargins(0, 10, 0, 10);
            boxParameters.setLayoutDirection(LinearLayout.HORIZONTAL);
            layout.addView(currentBox);

        }
        currentHouse = new LinearLayout(this);
        LinearLayout.LayoutParams houseParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        currentHouse.setLayoutParams(houseParameters);
        houseParameters.setMargins(5, 0, 5, 0);
        currentHouse.setOrientation(LinearLayout.VERTICAL);

        currentHouse.isClickable();
        currentHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



        TextView friendName = new TextView(this);
        friendName.setText(name);
        LinearLayout.LayoutParams textParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        friendName.setLayoutParams(textParameters);
        friendName.setGravity(Gravity.CENTER);

        Drawable img = getResources().getDrawable(R.drawable.hat_halo);
        ImageView image = new ImageView(this);
        LinearLayout.LayoutParams contentParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        image.setLayoutParams(contentParameters);
        image.setImageDrawable(img);
        contentParameters.gravity = Gravity.CENTER;



        currentHouse.addView(friendName);
        currentHouse.addView(image);
        currentBox.addView(currentHouse);
    }

}