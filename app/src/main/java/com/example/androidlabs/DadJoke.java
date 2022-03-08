package com.example.androidlabs;

import android.os.Bundle;
import android.widget.TextView;

public class DadJoke extends ActivityHeaderCreator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dad_joke);

        createActivityHeader();

        TextView dadJoke = findViewById(R.id.dadJoke);
        dadJoke.setText("How do celebrities stay cool? They have many fans.");
    }
}