package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        Bundle dataToSend = getIntent().getExtras();
        boolean isTablet = findViewById(R.id.fragment) != null;

        if(isTablet) {
            DetailsFragment dFragment = new DetailsFragment();
            dFragment.setArguments(dataToSend); //pass data to the the fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, dFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, EmptyActivity.class);
            startActivity (intent, dataToSend);
        }
    }
}