package com.pengembangsebelah.calculatorrab.myutils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import com.pengembangsebelah.calculatorrab.R;

import java.io.IOException;

public class PlayMusic {
    static String TAG = "Play Music Class";
    MediaPlayer mediaPlayer;
    AssetFileDescriptor afd;
    Context context;
    public PlayMusic(Context context){
        this.context = context;

    }

    public void Start(){
        try {
            afd = context.getAssets().openFd("sound.mp3");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            //mediaPlayer.start();
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(0.5f,0.5f);
            mediaPlayer.start();
        }catch (IOException err){
            Log.d(TAG, "PlayMusic: "+err.getMessage());
        }
    }
    public void Stop(){
        mediaPlayer.stop();
    }
    public boolean IsPlaying(){
        if(mediaPlayer==null)return false;
        else return mediaPlayer.isPlaying();
    }
}
