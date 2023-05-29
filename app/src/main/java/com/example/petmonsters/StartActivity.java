package com.example.petmonsters;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class StartActivity extends AppCompatActivity {

    private ImageView loginBtn;
    private ImageView registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getScreenElements();

        setButtonListeners();
    }



    private void getScreenElements(){
        loginBtn = findViewById(R.id.login_iv);
        registerBtn = findViewById(R.id.register_iv);
    }

    private void setButtonListeners(){
        loginBtn.setOnClickListener(view -> {
            Utilities.changeActivity(StartActivity.this, LoginActivity.class);
        });

        registerBtn.setOnClickListener(view -> {
            Utilities.changeActivity(StartActivity.this, RegisterActivity.class);
        });
    }
}
