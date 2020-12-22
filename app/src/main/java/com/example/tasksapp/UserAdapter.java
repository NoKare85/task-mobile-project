package com.example.tasksapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    List<User> users;
    Context context;

    public UserAdapter(Context c, List<User> u) {
        context = c;
        users = u;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.row_layout, null);
        TextView idTextView = (TextView) v.findViewById(R.id.text_view_id);
        TextView firstnameTextView = (TextView) v.findViewById(R.id.text_view_line1);
        TextView lastnameTextView = (TextView) v.findViewById(R.id.text_view_line2);

        String id = users.get(position).get_id();
        String firstname = users.get(position).getFirstname();
        String lastname = users.get(position).getLastname();

        idTextView.setText(id);
        firstnameTextView.setText(firstname);
        lastnameTextView.setText(lastname);

        return v;
    }
}

