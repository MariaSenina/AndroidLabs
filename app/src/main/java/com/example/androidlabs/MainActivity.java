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
    private ArrayList<ToDoItem> items = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.todoListView);

        CustomListAdapter adapter = new CustomListAdapter();
        listView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(click -> {
            Switch urgentSwitch = findViewById(R.id.urgentSwitch);
            EditText todoEntry = findViewById(R.id.todoEntry);
            ToDoItem newItem = new ToDoItem(todoEntry.getText().toString(), urgentSwitch.isChecked());
            items.add(newItem);
            todoEntry.setText("");
            urgentSwitch.setChecked(false);
            adapter.notifyDataSetChanged();
        });

        listView.setOnItemLongClickListener((p, b, pos, id) -> {
            View inflate = getLayoutInflater().inflate(R.layout.todo_layout, null);
            TextView text = inflate.findViewById(R.id.todoItem);
            text.setText(items.get(pos).getText());

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(getResources().getString(R.string.delete_question))
                    .setMessage(getResources().getString(R.string.selected_row) + " " + pos)
                    .setPositiveButton(getResources().getString(R.string.yes), (click, arg) -> {
                        items.remove(pos);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton(getResources().getString(R.string.no), (click, arg) -> {
                    })
                    .setView(inflate)
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
            textView.setText(((ToDoItem) getItem(position)).getText());

            if (((ToDoItem) getItem(position)).isUrgent()) {
                textView.setBackgroundColor(Color.RED);
                textView.setTextColor(Color.WHITE);
            } else {
                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
            }

            return newView;
        }
    }
}