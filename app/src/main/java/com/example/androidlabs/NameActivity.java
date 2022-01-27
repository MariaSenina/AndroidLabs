package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        Intent sentData = getIntent();
        String nameReceived = sentData.getStringExtra("name");

        TextView text = findViewById(R.id.welcomeText);
        String newWelcomeMessage = getResources().getString(R.string.welcome) + " " + nameReceived;
        text.setText(newWelcomeMessage);
    }
}