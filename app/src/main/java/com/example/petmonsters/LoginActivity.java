package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEt;
    private EditText passwordEt;
    private ImageView loginBtn;

    private PetMonstersApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getApp();

        getScreenElements();

        setOnClickListeners();
    }

    private void getApp(){
        app = (PetMonstersApplication) getApplicationContext();
    }

    private void getScreenElements(){
        usernameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn =  findViewById(R.id.login_iv);
    }

    private void setOnClickListeners(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                }
                catch (Exception e) { System.out.println(e.getMessage()); }
            }
        });
    }

    private void login() throws Exception {
        if (!validateUsername()){
            Utilities.ShowShortToast(getApplicationContext(),Constants.username_not_valid);
            return;
        }

        if (!validatePassword()){
            Utilities.ShowShortToast(getApplicationContext(),Constants.password_not_valid);
            return;
        }

        if (!validateCredentials()) {
            Utilities.ShowShortToast(getApplicationContext(), Constants.bad_user_password);
            return;
        }

        updateUserData();

        if (searchPet(app.getUser().getUsername())){
            updatePetData();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(LoginActivity.this, CharacterEditorActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private boolean validateCredentials() throws Exception {
        return DatabaseHandler.checkLogin(usernameEt.getText().toString(), Utilities.md5(passwordEt.getText().toString()));
    }

    private Boolean searchPet(String username){
        return DatabaseHandler.checkPet(username);
    }

    private void updateUserData() throws Exception {
        User user = DatabaseHandler.readUser(usernameEt.getText().toString(), true);
        ArrayList<Item> store = DatabaseHandler.readItems();
        app.setUser(user);
        app.setStore(store);
    }

    private void updatePetData() throws Exception {
        Pet pet = DatabaseHandler.readPet(usernameEt.getText().toString());
        app.setPet(pet);
    }

    private boolean validatePassword(){
        return !passwordEt.getText().toString().equals("");
    }

    private boolean validateUsername(){
        return !usernameEt.getText().toString().equals("");
    }
}