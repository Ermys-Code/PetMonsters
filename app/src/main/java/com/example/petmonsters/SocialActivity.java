package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getApplicationContext();
    }

    private void assignViewFields(){
        layout = findViewById();
    }

    private void generateFriends(){

        ArrayList<User> friends = new ArrayList<User>();

        for(int i = 0; i < friends.toArray().length; i++) {
            String name = friends.get(i).getUsername();
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
        LinearLayout.LayoutParams boxParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        currentBox.setLayoutParams(boxParameters);
        boxParameters.setMargins(5, 0, 5, 0);
        boxParameters.setLayoutDirection(LinearLayout.VERTICAL);

        currentHouse.isClickable();
        currentHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        currentBox.addView(currentHouse);


        TextView friendName = new TextView(this);
        friendName.setText(name);
        LinearLayout.LayoutParams contentParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        friendName.setLayoutParams(contentParameters);

        Drawable img = getResources().getDrawable(R.drawable.hat_halo);
        ImageView image = new ImageView(this);
        image.setLayoutParams(contentParameters);
        image.setImageDrawable(img);


        contentParameters.width = 500;
        contentParameters.height = 500;

        currentHouse.addView(image);
    }

}