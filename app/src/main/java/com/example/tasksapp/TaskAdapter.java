package com.example.tasksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    List<Task> tasks;
    Context context;

    public TaskAdapter(Context c, List<Task> t) {

        context = c;
        tasks = t;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.row_layout, null);
        TextView idTextView = (TextView) v.findViewById(R.id.text_view_id);
        TextView titleTextView = (TextView) v.findViewById(R.id.text_view_line1);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.text_view_line2);

        String id = tasks.get(position).get_id();
        String title = tasks.get(position).getTitle();
        String description = tasks.get(position).getDescription();

        idTextView.setText(id);
        titleTextView.setText(title);
        descriptionTextView.setText(description);

        return v;
    }
}

