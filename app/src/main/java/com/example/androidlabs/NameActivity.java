package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {
    int WRONG_NAME = 0;
    int OK_NAME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        Intent sentData = getIntent();
        String nameReceived = sentData.getStringExtra("name");

        TextView text = findViewById(R.id.welcomeText);

        String originalWelcome = getResources().getString(R.string.welcome);
        String welcome = originalWelcome.substring(0, originalWelcome.length() - 1);

        String newWelcomeMessage = welcome + " " + nameReceived + "!";
        text.setText(newWelcomeMessage);

        Button wrongNameButton = findViewById(R.id.wrongNameButton);
        wrongNameButton.setOnClickListener(click -> {
            setResult(WRONG_NAME);
            finish();
        });

        Button thankYouButton = findViewById(R.id.thankYouButton);
        thankYouButton.setOnClickListener(click -> {
            setResult(OK_NAME);
            finish();
        });
    }
}