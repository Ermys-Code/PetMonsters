package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.slider.Slider;

import org.json.JSONException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt;
    private EditText surnameEt;
    private EditText emailEt;
    private Slider ageSli;
    private EditText usernameEt;
    private EditText passwordEt;
    private ImageView registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getScreenElements();

        setButtonListeners();
    }

    private void getScreenElements(){
        nameEt = findViewById(R.id.name_et);
        surnameEt = findViewById(R.id.surname_et);
        emailEt = findViewById(R.id.email_et);
        ageSli = findViewById(R.id.age_sli);
        usernameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);
        registerBtn = findViewById(R.id.register_iv);
    }

    private void setButtonListeners(){
        registerBtn.setOnClickListener(v -> {
            try {
                register();
            }
            catch (Exception e) { System.out.println(e.getMessage()); }
        });
    }

    private void register() throws Exception {
        if(!validateEmail()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.email_not_valid);
            return;
        }

        if (!validateAge()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.age_not_valid);
            return;
        }

        if (!validateUsername()){
            Utilities.ShowShortToast(getApplicationContext(),Constants.username_not_valid);
            return;
        }
        if (!validateName()){
            Utilities.ShowShortToast(getApplicationContext(),Constants.name_not_valid);
            return;
        }
        if (!validateSurname()){
            Utilities.ShowShortToast(getApplicationContext(),Constants.surname_not_valid);
            return;
        }
        if (!usernameExists()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.username_not_available);
            return;
        }
        if (!validatePassword()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.password_not_valid);
            return;
        }

        User newUser = createUser();
        if(!DatabaseHandler.createUser(newUser)){
            Utilities.ShowShortToast(getApplicationContext(), Constants.failed_create_user);
            return;
        } else {
            Utilities.ShowShortToast(getApplicationContext(), Constants.user_created);

            PetMonstersApplication app = (PetMonstersApplication) getApplicationContext();
            app.setUser(newUser);

            ArrayList<Item> store = DatabaseHandler.readItems();
            app.setStore(store);

            Intent intent = new Intent(RegisterActivity.this, CharacterEditorActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private boolean validateEmail(){
        return emailEt.getText().toString().contains(Constants.at);
    }

    private boolean validateAge(){
        return ageSli.getValue() >= 1;
    }

    private boolean validateUsername(){
        return !usernameEt.getText().toString().equals("");
    }

    private boolean validateName(){
        return !nameEt.getText().toString().equals("");
    }

    private boolean validateSurname(){
        return !surnameEt.getText().toString().equals("");
    }

    private boolean usernameExists(){
        try {
            DatabaseHandler.readUser(usernameEt.getText().toString(), false);
            return false;
        }
        catch (Exception e) { return true; }
    }

    private boolean validatePassword(){
        return !passwordEt.getText().toString().equals("");
    }

    private User createUser() throws Exception {
        String name = nameEt.getText().toString();
        String surname = surnameEt.getText().toString();
        int age = (int)ageSli.getValue();
        String email = emailEt.getText().toString();
        String username = usernameEt.getText().toString();
        String password = Utilities.md5(passwordEt.getText().toString());
        ArrayList<String> items = new ArrayList<>();
        ArrayList<User> friends = new ArrayList<>();



        User newUser = new User(name, surname, email, username, password, age, items, friends, 500);

        return newUser;
    }
}