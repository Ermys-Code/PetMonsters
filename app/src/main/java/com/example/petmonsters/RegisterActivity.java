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
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try { register(); }
                catch (NoSuchAlgorithmException | ExecutionException | InterruptedException e) { e.printStackTrace(); }
            }
        });
    }

    private void register() throws NoSuchAlgorithmException, ExecutionException, InterruptedException {
        if(!validateEmail()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.email_not_valid);
            return;
        }

        if (!validateAge()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.birthadte_not_valid);
            return;
        }

        if (!validateUsername()){
            Utilities.ShowShortToast(getApplicationContext(),Constants.username_not_valid);
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
            Utilities.ShowShortToast(getApplicationContext(), "Failed to create user");
            return;
        } else {
            Utilities.ShowShortToast(getApplicationContext(), "User created succesfully");

            PetMonstersApplication app = (PetMonstersApplication) getApplicationContext();
            app.setUser(newUser);

            Intent intent = new Intent(RegisterActivity.this, CharacterEditorActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private boolean validateEmail(){
        //if
        return true;
    }

    private boolean validateAge(){
        //if
        return true;
    }

    private boolean validateUsername(){
        //if
        return true;
    }

    private boolean usernameExists(){
        try {
            DatabaseHandler.readUser(usernameEt.getText().toString(), false);
            return false;
        } catch (JSONException | ExecutionException | InterruptedException e) {
            return true;
        }
    }

    private boolean validatePassword(){
        //if
        return true;
    }

    private User createUser() throws NoSuchAlgorithmException {

        String name = nameEt.getText().toString();
        String surname = surnameEt.getText().toString();
        int age = (int)ageSli.getValue();
        String email = emailEt.getText().toString();
        String username = usernameEt.getText().toString();
        String password = Utilities.md5(passwordEt.getText().toString());


        User newUser = new User(name, surname, email, username, password, age, null, 0, null);

        return newUser;
    }
}