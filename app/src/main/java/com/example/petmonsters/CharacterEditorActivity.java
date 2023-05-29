package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CharacterEditorActivity extends AppCompatActivity {

    private EditText namePetEt;
    private ImageView formerPetIv;
    private ImageView petIv;
    private ImageView nextPetIv;
    private ImageView color1Iv;
    private ImageView color2Iv;
    private ImageView color3Iv;
    private ImageView color4Iv;
    private Button savePetBtn;

    private ArrayList<Model> modelsArray = new ArrayList<>();

    private String modelName;
    private ArrayList<String> modelSprites = new ArrayList<>();
    private int currentSprite = 0;
    private ArrayList<String> modelColors = new ArrayList<>();
    private int currentColor = 0;
    private ArrayList<String> modelTags = new ArrayList<>();

    private int currentPet = 0;

    private PetMonstersApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_editor);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getAppContext();

        getScreenElements();

        setOnClickListeners();

        try { getModelsData(); } catch (ExecutionException | InterruptedException | JSONException e) { e.printStackTrace(); }

        loadModelData(currentPet);
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getApplicationContext();
    }

    private void getScreenElements(){
        namePetEt = findViewById(R.id.namePet_et);
        formerPetIv = findViewById(R.id.formerPet_iv);
        petIv = findViewById(R.id.pet_iv);
        nextPetIv = findViewById(R.id.nextPet_iv);
        color1Iv = findViewById(R.id.color1_iv);
        color2Iv = findViewById(R.id.color2_iv);
        color3Iv = findViewById(R.id.color3_iv);
        color4Iv = findViewById(R.id.color4_iv);
        savePetBtn = findViewById(R.id.savePet_btn);
    }

    private void setOnClickListeners(){
        formerPetIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePet(-1);
            }
        });

        nextPetIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePet(1);
            }
        });

        color1Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(0);
            }
        });

        color2Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(1);
            }
        });

        color3Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(2);
            }
        });

        color4Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(3);
            }
        });

        savePetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try { savePet(); }
                catch (ExecutionException | InterruptedException e) { e.printStackTrace(); }
            }
        });
    }

    private void getModelsData() throws ExecutionException, InterruptedException, JSONException {
        modelsArray = DatabaseHandler.getModels();
    }

    private void changePet(int num){
        currentPet += num;
        if (currentPet >= modelsArray.size()) currentPet = 0;
        else if (currentPet < 0) currentPet = modelsArray.size() - 1;
        loadModelData(currentPet);
    }

    private void changeColor(int num){
        currentColor = num;
        currentSprite = num;
        Resources res = app.getResources();
        int drawableId = res.getIdentifier(modelSprites.get(currentSprite), "drawable", getApplicationContext().getPackageName());
        petIv.setImageDrawable(getResources().getDrawable(drawableId));
    }

    private void loadModelData(int pet){
        Model model = modelsArray.get(pet);

        modelName = model.getName();
        modelSprites = model.getSprites();
        modelColors = model.getColors();
        modelTags = model.getTags();

        currentSprite = 0;
        currentColor = 0;

        setModelData();
    }

    private void setModelData(){
        namePetEt.setText(modelName);
        Resources res = app.getResources();
        int drawableId = res.getIdentifier(modelSprites.get(currentSprite), "drawable", getApplicationContext().getPackageName());
        petIv.setImageDrawable(getResources().getDrawable(drawableId));
        color1Iv.setColorFilter(Color.parseColor(modelColors.get(0)), PorterDuff.Mode.MULTIPLY);
        color2Iv.setColorFilter(Color.parseColor(modelColors.get(1)), PorterDuff.Mode.MULTIPLY);
        color3Iv.setColorFilter(Color.parseColor(modelColors.get(2)), PorterDuff.Mode.MULTIPLY);
        color4Iv.setColorFilter(Color.parseColor(modelColors.get(3)), PorterDuff.Mode.MULTIPLY);
    }


    private void savePet() throws ExecutionException, InterruptedException {


        Pet pet = new Pet(app.getUser().getUsername(), modelSprites.get(currentSprite), modelColors.get(currentColor), "", "", namePetEt.getText().toString(), modelTags);
        if(DatabaseHandler.createPet(pet)){
            Utilities.ShowShortToast(getApplicationContext(), "Pet created successfully");
            app.setPet(pet);

            Intent intent = new Intent(CharacterEditorActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Utilities.ShowShortToast(getApplicationContext(), "Error creating pet");
        }
    }
}