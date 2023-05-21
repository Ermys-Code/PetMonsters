package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.LocusId;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    final private EditText emailEt = findViewById(R.id.email_et);
    final private EditText passwordEt = findViewById(R.id.password_et);
    final private Button loginBtn = findViewById(R.id.login_btn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setOnClickListeners();
    }

    private void setOnClickListeners(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        if (!validateCredentials()){
            Utilities.ShowShortToast(getApplicationContext(), getString(R.string.bad_user_password));
            return;
        }

        updateUserData();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateCredentials(){
        //connect to database
        //check if email exist
            //if not return false
        //check if password is correct
            //if not return false
        return true;
    }

    private void updateUserData(){
        PetMonstersApplication app = (PetMonstersApplication) getApplicationContext();
        User user = UserCRUD.getUser();

        app.setUser(user);
    }
}