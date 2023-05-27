package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
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
                catch (NoSuchAlgorithmException | ExecutionException | InterruptedException | JSONException | SQLException e) { e.printStackTrace(); }
            }
        });
    }

    private void login() throws NoSuchAlgorithmException, ExecutionException, InterruptedException, JSONException, SQLException {
        if (!validateCredentials()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.bad_user_password);
            return;
        }

        updateUserData();

        //buscar mascota
            //if exist
            updatePetData();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            //else
            //ir creador
    }

    private boolean validateCredentials() throws NoSuchAlgorithmException, JSONException, ExecutionException, InterruptedException {
        return DatabaseHandler.checkLogin(usernameEt.getText().toString(), Utilities.md5(passwordEt.getText().toString()));
    }

    private void updateUserData() throws ExecutionException, InterruptedException, JSONException, SQLException {
        PetMonstersApplication app = (PetMonstersApplication) getApplicationContext();
        User user = DatabaseHandler.readUser(usernameEt.getText().toString());
        ArrayList<Item> store = DatabaseHandler.readItems();
        app.setUser(user);
        app.setStore(store);
    }

    private void updatePetData() throws JSONException, ExecutionException, InterruptedException {
        PetMonstersApplication app = (PetMonstersApplication) getApplicationContext();
        Pet pet = DatabaseHandler.readPet(usernameEt.getText().toString());
        app.setPet(pet);
    }
}