package com.example.androidlabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<SWCharacter> characters;
    private Context context;

    public CustomListAdapter(Context context, List<SWCharacter> characters) {
        this.characters = characters;
        this.context = context;
    }

    @Override
    public int getCount() {
        return characters.size();
    }

    @Override
    public Object getItem(int position) {
        return characters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((SWCharacter)getItem(position)).getId();
    }

    @Override
    public View getView(int position, View oldView, ViewGroup parent) {
        View newView = oldView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (newView == null) {
            newView = inflater.inflate(R.layout.layout_list_view_row_layout, null, true);
        }

        TextView textView = newView.findViewById(R.id.textView);
        textView.setText(((SWCharacter)getItem(position)).getName());

        return newView;
    }
}
