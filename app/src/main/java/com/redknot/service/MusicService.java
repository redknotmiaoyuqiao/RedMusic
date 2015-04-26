package com.redknot.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.os.IBinder;

/**
 * Created by lenovo on 2015/4/26.
 */
public class MusicService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    MediaPlayer play;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        try {
            play.reset();
            play.setDataSource(intent.getStringExtra("path"));
            play.prepare();
            play.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();

        play = new MediaPlayer();

        BassBoost b = new BassBoost(0, play.getAudioSessionId());
        b.setEnabled(true);
        b.setStrength((short) 1000);


    }
}
