package com.studio49.mobi_mss_b_ass1;



import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


@SuppressLint("ResourceAsColor")
public class UsersAdapter extends ArrayAdapter<User> {
    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView home;
    }

    public UsersAdapter(Context context, LinkedList<User> users) {
        super(context, R.layout.item_user, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_user, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.home = (TextView) convertView.findViewById(R.id.tvHome);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.name.setText(user.name);
        viewHolder.home.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}