package com.redknot.thread;

import java.io.File;
import android.os.Handler;
import android.os.Message;

/**
 * Created by lenovo on 2015/4/25.
 */
public class FileListThread implements Runnable{

    private File file;
    private Handler handler;

    public FileListThread(File file ,Handler handler){
        this.file = file;
        this.handler = handler;
    }

    @Override
    public void run() {
        getFile(file);
    }

    private void getFile(File file){

        File files[] = file.listFiles();
        if(files != null){
            for(File f : files){
                if(f.isDirectory()){
                    getFile(f);
                }
                else{
                    if(f.getName().toString().endsWith(".mp3")) {
                        Message msg = new Message();
                        msg.obj = f;
                        this.handler.sendMessage(msg);
                        //System.out.println(f.getAbsolutePath());
                    }
                }
            }
        }
    }
}
