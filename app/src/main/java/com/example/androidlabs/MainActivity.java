package com.example.androidlabs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.todoListView);

        CustomListAdapter adapter = new CustomListAdapter();
        listView.setAdapter(adapter);

        EditText todoEntry = findViewById(R.id.todoEntry);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(click -> {
            String newEntry = todoEntry.getText().toString();
            items.add(newEntry);
            todoEntry.setText("");
            adapter.notifyDataSetChanged();
        });


        listView.setOnItemLongClickListener((p, b, pos, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Do you want to delete this?")
                    .setMessage("The selected row is " + pos)
                    .setPositiveButton("Yes", (click, arg) -> {
                        items.remove(pos);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", (click, arg) -> {})
                    .setView(getLayoutInflater().inflate(R.layout.todo_layout, null))
                    .create().show();
            return true;
        });
    }

    private class CustomListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View oldView, ViewGroup parent) {
            View newView = oldView;

            LayoutInflater inflater = getLayoutInflater();

            if (newView == null) {
                newView = inflater.inflate(R.layout.todo_layout, parent, false);
            }

            TextView textView = newView.findViewById(R.id.todoItem);
            textView.setText( getItem(position).toString() );

            return newView;
        }
    }
}