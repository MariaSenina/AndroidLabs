package com.example.androidlabs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    int START_NAME_ACTIVITY = 123;

    SharedPreferences preferences;
    EditText typeField;
    Intent nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
        String savedString = preferences.getString("ReserveName", "");
        typeField = findViewById(R.id.inputText);
        typeField.setText(savedString);

        Button nextButton = findViewById(R.id.nextButton);
        nextPage = new Intent(this, NameActivity.class);
        nextButton.setOnClickListener(click -> {
            nextPage.putExtra("name", typeField.getText().toString());
            startActivityForResult(nextPage, START_NAME_ACTIVITY);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveSharedPreferences(typeField.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == START_NAME_ACTIVITY) {
            if (resultCode == 1) {
                finish();
            } else if (resultCode == 0) {
                typeField.setText("");
            }
        }
    }

    private void saveSharedPreferences(String stringToSave) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ReserveName", stringToSave);
        editor.commit();
    }
}