package com.redknot.music;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.redknot.service.MusicService;

/**
 * Created by lenovo on 2015/4/26.
 */
public class PlayActivity extends Activity {

    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        show = (TextView)findViewById(R.id.show);


        String music_name = getIntent().getStringExtra("music_name");
        String music_path = getIntent().getStringExtra("music_path");

        show.setText(music_name + "\n" + music_path);



        Intent intent = new Intent(PlayActivity.this, MusicService.class);
        intent.putExtra("path",music_path);
        startService(intent);

    }
}
