package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

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
    private ArrayList<Bitmap> modelSprites = new ArrayList<>();
    private int currentSprite = 0;
    private ArrayList<Integer> modelColors = new ArrayList<>();

    private int currentPet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_editor);

        getScreenElements();

        setOnClickListeners();

        getModelsData();

        loadModelData(currentPet);
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
    }

    private void getModelsData(){
        modelsArray = DatabaseHandler.getModels();
    }

    private void changePet(int num){
        currentPet += num;
        if (currentPet >= modelsArray.size()) currentPet = 0;
        else if (currentPet < 0) currentPet = modelsArray.size() - 1;
        loadModelData(currentPet);
    }

    private void changeColor(int num){
        currentSprite = num;
        petIv.setImageBitmap(modelSprites.get(currentSprite));
    }

    private void loadModelData(int pet){
        Model model = modelsArray.get(pet);

        modelName = model.getName();
        modelSprites = model.getSprites();
        modelColors = model.getColors();

        currentSprite = 0;

        setModelData();
    }

    private void setModelData(){
        namePetEt.setText(modelName);
        petIv.setImageBitmap(modelSprites.get(currentSprite));
        color1Iv.setColorFilter(modelColors.get(0));
        color2Iv.setColorFilter(modelColors.get(1));
        color3Iv.setColorFilter(modelColors.get(2));
        color4Iv.setColorFilter(modelColors.get(3));
    }
}