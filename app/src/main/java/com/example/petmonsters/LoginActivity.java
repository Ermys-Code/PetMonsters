package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getScreenElements();

        setOnClickListeners();
    }

    private void getScreenElements(){
        usernameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn =  findViewById(R.id.login_btn);
    }

    private void setOnClickListeners(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {login();}
                catch (NoSuchAlgorithmException | ExecutionException | InterruptedException | JSONException e) { e.printStackTrace(); }
            }
        });
    }

    private void login() throws NoSuchAlgorithmException, ExecutionException, InterruptedException, JSONException {
        if (!validateCredentials()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.bad_user_password);
            return;
        }

        updateUserData();

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateCredentials() throws NoSuchAlgorithmException, JSONException, ExecutionException, InterruptedException {
        if(!DatabaseHandler.checkLogin(usernameEt.getText().toString(), passwordEt.getText().toString())) return false;
        //if(!DatabaseHandler.checkPassword(usernameEt.getText().toString(), Utilities.md5(passwordEt.getText().toString()))) return false;

        return true;
    }

    private void updateUserData() throws ExecutionException, InterruptedException, JSONException {
        PetMonstersApplication app = (PetMonstersApplication) getApplicationContext();
        User user = DatabaseHandler.readUser(usernameEt.getText().toString());
        Pet pet = DatabaseHandler.readPet(usernameEt.getText().toString());
        app.setUser(user);
        app.setPet(pet);
    }
}