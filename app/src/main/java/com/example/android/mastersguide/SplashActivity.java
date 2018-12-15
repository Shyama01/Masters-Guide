package com.example.android.mastersguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {


    private Button splashBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashBtn = (Button)findViewById(R.id.splashBtn);
        splashBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}