package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    final private EditText nameEt = findViewById(R.id.name_et);
    final private EditText surnameEt = findViewById(R.id.surname_et);
    final private EditText emailEt = findViewById(R.id.email_et);
    final private EditText birthdateEt = findViewById(R.id.birthdate_et);
    final private EditText usernameEt = findViewById(R.id.username_et);
    final private EditText passwordEt = findViewById(R.id.password_et);
    final private Button registerBtn = findViewById(R.id.register_btn);

    final private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setButtonListeners();
    }

    private void setButtonListeners(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    register();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void register() throws ParseException {
        if(!validateEmail()){
            Utilities.ShowShortToast(getApplicationContext(), getString(R.string.email_not_valid));
            return;
        }

        if (!validateBirthday()){
            Utilities.ShowShortToast(getApplicationContext(), getString(R.string.birthadte_not_valid));
            return;
        }

        if (!validateUsername()){
            Utilities.ShowShortToast(getApplicationContext(), getString(R.string.username_not_valid));
            return;
        }
        if (!usernameExists()){
            Utilities.ShowShortToast(getApplicationContext(), getString(R.string.username_not_available));
            return;
        }

        if (!validatePassword()){
            Utilities.ShowShortToast(getApplicationContext(), getString(R.string.password_not_valid));
            return;
        }

        User newUser = createUser();
        UserCRUD.createUser(newUser);
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

    private User createUser() throws ParseException {
        User newUser = new User();

        newUser.setName(nameEt.getText().toString());
        newUser.setSurname(surnameEt.getText().toString());
        Date date = format.parse(birthdateEt.getText().toString());
        newUser.setBirthdate(date);
        newUser.setEmail(emailEt.getText().toString());
        newUser.setUsername(usernameEt.getText().toString());

        return newUser;
    }
}