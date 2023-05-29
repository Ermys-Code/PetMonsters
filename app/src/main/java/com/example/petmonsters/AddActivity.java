package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AddActivity extends AppCompatActivity {

    private PetMonstersApplication app;
    private ImageView backgroundIv, closeIv;
    private Class<?> nextClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getAppContext();

        getIntentData();

        getScreenElements();

        setOnClickListeners();

        try { getUserTags(); }
        catch (JSONException | ExecutionException | InterruptedException e) { e.printStackTrace(); }

        getFriendsTags();

        joinTags();

        mapReduce();

        getAdd();

        loadAdd();
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getApplicationContext();
    }

    private void getIntentData(){
        nextClass = (Class<?>) getIntent().getSerializableExtra("nextActivity");
    }

    private void getScreenElements(){
        backgroundIv = findViewById(R.id.background_iv);
        closeIv = findViewById(R.id.close_iv);
    }

    private void setOnClickListeners(){
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, nextClass);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getUserTags() throws JSONException, ExecutionException, InterruptedException {
        ArrayList<String> itemTags = new ArrayList<>();
        ArrayList<String> petTags = new ArrayList<>();

        DatabaseHandler.readItemTags(app.getPet().getHat());
        //get tags shoes
    }

    private void getFriendsTags(){

    }

    private void joinTags(){

    }

    private void mapReduce(){

    }

    private void getAdd(){

    }

    private void loadAdd(){

    }
}