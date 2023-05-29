package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AddActivity extends AppCompatActivity {

    private PetMonstersApplication app;
    private ImageView backgroundIv, closeIv;
    private Class<?> nextClass;
    private ArrayList<String> tagsArray = new ArrayList<>();
    private Drawable add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getAppContext();

        getIntentData();

        getScreenElements();

        setOnClickListeners();

        try {
            getUserTags();
            getFriendsTags();
            map(tagsArray);
        }
        catch (JSONException | ExecutionException | InterruptedException e) { e.printStackTrace(); }

        input();

        shuffle();

        reduce();

        getAdd();

        loadAdd(add);
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

        ArrayList<String> hatTags = DatabaseHandler.readItemTags(app.getPet().getHat());
        ArrayList<String> shoesTags = DatabaseHandler.readItemTags(app.getPet().getShoes());

        for (int i = 0; i < 4; i++) {
            itemTags.addAll(hatTags);
            itemTags.addAll(shoesTags);
        }
        for (int i = 0; i < 3; i++){
            petTags.addAll(app.getPet().getTags());
        }

        String stringItemTags = String.join(",", itemTags);
        String stringPetTags = String.join(",", petTags);

        tagsArray.add(stringItemTags);
        tagsArray.add(stringPetTags);

    }

    private void getFriendsTags() throws JSONException, ExecutionException, InterruptedException {
        for (User friend : app.getUser().getFriends()){
            Pet pet = DatabaseHandler.readPet(friend.getUsername());

            ArrayList<String> itemTags = new ArrayList<>();
            ArrayList<String> petTags = new ArrayList<>();

            ArrayList<String> hatTags = DatabaseHandler.readItemTags(pet.getHat());
            ArrayList<String> shoesTags = DatabaseHandler.readItemTags(pet.getShoes());

            itemTags.addAll(hatTags);
            itemTags.addAll(shoesTags);
            petTags.addAll(pet.getTags());

            String stringItemTags = String.join(",", itemTags);
            String stringPetTags = String.join(",", petTags);

            tagsArray.add(stringItemTags);
            tagsArray.add(stringPetTags);
        }

        System.out.println(tagsArray);
        System.out.println(tagsArray);

    }

    private void input(){

    }

    private void map(ArrayList<String> tags) throws ExecutionException, InterruptedException {

        ArrayList<String> newTagsList = new ArrayList<>();
        for(int i=0; i < tags.size(); i++){
            newTagsList.add(new AsyncMap().execute(tags.get(i)).get());
        }
    }

    private void shuffle(){

    }

    private void reduce(){

    }

    private void getAdd(String theme) throws JSONException, ExecutionException, InterruptedException {
        String addString = DatabaseHandler.getAdd(theme);

        Resources res = app.getResources();
        int drawableId = res.getIdentifier(addString, "drawable", app.getContext().getApplicationContext().getPackageName());
        add = app.getContext().getResources().getDrawable(drawableId);
    }

    private void loadAdd(Drawable add){
        backgroundIv.setImageDrawable(add);
    }
}