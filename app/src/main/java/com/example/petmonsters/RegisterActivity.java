package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.slider.Slider;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt;
    private EditText surnameEt;
    private EditText emailEt;
    private Slider ageSli;
    private EditText usernameEt;
    private EditText passwordEt;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        registerBtn = findViewById(R.id.register_btn);
    }

    private void setButtonListeners(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try { register(); }
                catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
            }
        });
    }

    private void register() throws NoSuchAlgorithmException {
        if(!validateEmail()){
            Utilities.ShowShortToast(getApplicationContext(), Constants.email_not_valid);
            return;
        }

        if (!validateBirthday()){
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
        DatabaseHandler.createUser(newUser);
    }

    private boolean validateEmail(){
        //if
        return true;
    }

    private boolean validateBirthday(){
        //if
        return true;
    }

    private boolean validateUsername(){
        //if
        return true;
    }

    private boolean usernameExists(){
        //if
        return true;
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

        User newUser = new User(name, surname, email, username, password, age, null);

        return newUser;
    }
}