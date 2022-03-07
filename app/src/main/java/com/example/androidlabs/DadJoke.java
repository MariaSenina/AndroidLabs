package com.example.androidlabs;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DadJoke extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View newView = inflater.inflate(R.layout.activity_dad_joke, container, false);

        TextView dadJoke = newView.findViewById(R.id.dadJoke);
        dadJoke.setText("How do celebrities stay cool? They have many fans.");

        return newView;
    }
}