package com.redknot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.redknot.music.R;

import java.io.File;
import java.util.List;

/**
 * Created by lenovo on 2015/4/25.
 */
public class MusicAdapter extends BaseAdapter {

    private List<File> data;
    private Context context;

    public MusicAdapter(List<File> data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        view = inflater.inflate(R.layout.listview_music, null);

        TextView music_name = (TextView) view.findViewById(R.id.music_name);

        music_name.setText(this.data.get(position).getName());

        return view;
    }
}
