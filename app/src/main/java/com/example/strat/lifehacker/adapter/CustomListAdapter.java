package com.example.strat.lifehacker.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.strat.lifehacker.R;
import com.example.strat.lifehacker.model.News;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<News> movieItems;

    public CustomListAdapter(Activity activity, ArrayList<News> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int position) {
        return movieItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        ImageView icon = (ImageView) convertView.findViewById(R.id.thumbnail);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        News n = movieItems.get(position);
        // title
        title.setText(n.getTitle());
        // icon

        if (n.getImg() == null)
            icon.setImageResource(R.drawable.no_image);
        else
            icon.setImageBitmap(n.getImg());
        return convertView;
    }

    public void updateAdaper(ArrayList<News> arr) {
        movieItems = arr;
        notifyDataSetChanged();
    }
}
