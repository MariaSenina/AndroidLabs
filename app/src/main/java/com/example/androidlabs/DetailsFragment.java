package com.example.androidlabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    private Bundle receivedData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        receivedData = getArguments();
        View newView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView namePlaceholder = newView.findViewById(R.id.namePlaceholder);
        namePlaceholder.setText(receivedData.getString(MainActivity.NAME));

        TextView heightPlaceholder = newView.findViewById(R.id.heightPlaceholder);
        heightPlaceholder.setText(receivedData.getString(MainActivity.HEIGHT));

        TextView massPlaceholder = newView.findViewById(R.id.massPlaceholder);
        massPlaceholder.setText(receivedData.getString(MainActivity.MASS));

        return newView;
    }
}