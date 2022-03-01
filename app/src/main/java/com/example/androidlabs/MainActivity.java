package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String HEIGHT = "HEIGHT";
    public static final String MASS = "MASS";

    ArrayAdapter<String> theAdapter;

    ArrayList<String> source = new ArrayList<>(Arrays.asList("One", "Two", "Three", "Four"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        boolean isTablet = findViewById(R.id.fragment) != null;

        theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, source);
        listView.setAdapter(theAdapter);
        listView.setOnItemClickListener((list, item, position, id) -> {
            Bundle dataToSend = new Bundle();
            dataToSend.putString(NAME, "Test Name");
            dataToSend.putString(HEIGHT, "123");
            dataToSend.putString(MASS, "11");

            if(isTablet) {
                DetailsFragment detailFragment = new DetailsFragment();
                detailFragment.setArguments(dataToSend);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, detailFragment)
                        .commit();
            } else {
                Intent nextActivity = new Intent(MainActivity.this, EmptyActivity.class);
                nextActivity.putExtras(dataToSend);
                startActivity(nextActivity);
            }
        });
    }
}