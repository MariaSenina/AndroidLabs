package com.example.androidlabs;

import static com.example.androidlabs.CustomOpener.COL_ID;
import static com.example.androidlabs.CustomOpener.COL_TODO_ITEM;
import static com.example.androidlabs.CustomOpener.COL_URGENT;
import static com.example.androidlabs.CustomOpener.TABLE_NAME;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ToDoItem> items = new ArrayList();
    private final static int TRUE = 1;
    private final static int FALSE = 0;
    SQLiteDatabase sqLiteDatabase;
    ToDoItem newItem;
    Cursor todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadItemsFromDatabase();
        printCursor(todoList);

        ListView listView = findViewById(R.id.todoListView);

        CustomListAdapter adapter = new CustomListAdapter();
        listView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(click -> {
            Switch urgentSwitch = findViewById(R.id.urgentSwitch);
            EditText todoEntry = findViewById(R.id.todoEntry);
            String todoItem = todoEntry.getText().toString();
            boolean urgent = urgentSwitch.isChecked();

            // Add new to-do item to the database
            ContentValues newRowValues = new ContentValues();
            newRowValues.put(COL_TODO_ITEM, todoItem);
            if (urgent) {
                newRowValues.put(COL_URGENT, TRUE);
            } else {
                newRowValues.put(COL_URGENT, FALSE);
            }
            long id = sqLiteDatabase.insert(TABLE_NAME, null, newRowValues);

            newItem = new ToDoItem(todoItem, urgent, id);
            items.add(newItem);

            // reset input area
            todoEntry.setText("");
            urgentSwitch.setChecked(false);

            // notify adapter about change
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
                        sqLiteDatabase.delete(TABLE_NAME, COL_ID + " = " + adapter.getItemId(pos), null);
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

    private void loadItemsFromDatabase() {
        CustomOpener dbOpener = new CustomOpener(this);
        sqLiteDatabase = dbOpener.getWritableDatabase();

        //get all rows from the to-do-list table
        todoList = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        int todoItemIndex = todoList.getColumnIndex(COL_TODO_ITEM);
        int urgentIndex = todoList.getColumnIndex(COL_URGENT);
        int idIndex = todoList.getColumnIndex(COL_ID);

        // iterate over the results
        while (todoList.moveToNext()) {
            String todoItem = todoList.getString(todoItemIndex);
            boolean urgent = false;
            if (todoList.getInt(urgentIndex) == TRUE) {
                urgent = true;
            }
            long id = todoList.getLong(idIndex);

            // add retrieved item to the ArrayList for displaying
            items.add(new ToDoItem(todoItem, urgent, id));
        }
    }

    private void printCursor(Cursor c) {
        // db version
        System.out.println("DB version: " + sqLiteDatabase.getVersion());

        // number of columns
        System.out.println("Column count: " + c.getColumnCount());

        // names of the columns
        System.out.println("Column names: " + Arrays.toString(c.getColumnNames()));

        // number of results in the cursor
        System.out.println("Number of rows: " + c.getCount());

        // all rows in the cursor
        System.out.println("Row contents: \n" + items.toString());
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
            return ((ToDoItem)getItem(position)).getId();
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