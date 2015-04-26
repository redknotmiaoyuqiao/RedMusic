package com.redknot.music;

import android.content.Intent;
import android.os.Environment;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.redknot.adapter.MusicAdapter;
import com.redknot.thread.FileListThread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

public class MainActivity extends ActionBarActivity {

    MyHandler handelr;

    private ListView listview;
    private MusicAdapter adapter;

    private List<File> data = new ArrayList<File>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handelr = new MyHandler();
        adapter = new MusicAdapter(data,this);

        File sd = Environment.getExternalStorageDirectory();
        FileListThread f_thread = new FileListThread(sd, handelr);
        new Thread(f_thread).start();

        listview = (ListView) findViewById(R.id.listview);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);

                Intent intent = new Intent(MainActivity.this,PlayActivity.class);

                intent.putExtra("music_name",data.get(position).getName().toString());
                intent.putExtra("music_path",data.get(position).getAbsolutePath().toString());

                startActivity(intent);
            }
        });

    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            File f = (File)msg.obj;
            data.add(f);

            adapter.notifyDataSetChanged();

            System.out.println(f.getName());
        }
    }

}
