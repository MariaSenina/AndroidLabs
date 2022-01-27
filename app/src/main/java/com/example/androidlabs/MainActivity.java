package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    EditText typeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
        String savedString = preferences.getString("ReserveName", "");
        typeField = findViewById(R.id.inputText);
        typeField.setText(savedString);

        Button nextButton = findViewById(R.id.nextButton);
        Intent nextPage = new Intent(this, NameActivity.class);
        nextButton.setOnClickListener(click -> {
            startActivity(nextPage);

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveSharedPreferences(typeField.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void saveSharedPreferences(String stringToSave) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ReserveName", stringToSave);
        editor.commit();
    }
}