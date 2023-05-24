package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_editor);

        getScreenElements();

        getModelsData();
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

    private void getModelsData(){

    }
}