package com.example.androidlabs;

import android.os.Bundle;

public class MainActivity extends ActivityHeaderCreator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createActivityHeader();
    }
}