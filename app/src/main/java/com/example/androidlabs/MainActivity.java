package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_constraint);

        Button btn = findViewById(R.id.press_me_button);
        EditText edit = findViewById(R.id.edit_text);
        TextView text = findViewById(R.id.text1);

        btn.setOnClickListener( click -> {
            text.setText( edit.getText().toString() );
            Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_message) , Toast.LENGTH_SHORT).show();
            } );
    }
}