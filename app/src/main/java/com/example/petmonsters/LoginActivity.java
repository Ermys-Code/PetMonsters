package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;

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
                catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
            }
        });
    }

    private void login() throws NoSuchAlgorithmException {
        if (!validateCredentials()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.bad_user_password);
            return;
        }

        updateUserData();

        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateCredentials() throws NoSuchAlgorithmException {
        if(!DatabaseHandler.checkUsername(usernameEt.getText().toString())) return false;
        if(!DatabaseHandler.checkPassword(usernameEt.getText().toString(), Utilities.md5(passwordEt.getText().toString()))) return false;
        return true;
    }

    private void updateUserData(){
        PetMonstersApplication app = (PetMonstersApplication) getApplicationContext();
        User user = DatabaseHandler.readUser(usernameEt.getText().toString());
        System.out.println(user);
        app.setUser(user);
    }
}