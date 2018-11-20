package com.zhq.androidwearvideo;

/**
 * @author: ZhangHq
 * @blog: https://blog.csdn.net/zhq9695
 * @github: https://github.com/zhq9695
 * @date: 2018.11.16
 */

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends WearableActivity {

    private VideoView videoPlayer;

    private File tempFile;
    private InputStream inputStream;
    private OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize vitamio
        Vitamio.isInitialized(getApplicationContext());

        setContentView(R.layout.activity_main);

        // create temporary file with video.mp4
        try {
            inputStream = MainActivity.this.getResources().openRawResource(R.raw.video);
            tempFile = File.createTempFile("video", "mov");
            outputStream = new FileOutputStream(tempFile);

            final byte[] buffer = new byte[102400];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        videoPlayer = findViewById(R.id.videoPlayer);
        videoPlayer.setVideoPath(tempFile.getPath());
        videoPlayer.setMediaController(new MediaController(this));
        videoPlayer.requestFocus();

        // set prepared listener
        videoPlayer.setOnPreparedListener(new io.vov.vitamio.MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(io.vov.vitamio.MediaPlayer mp) {
                mp.seekTo(0);
                mp.setPlaybackSpeed(1.0f);
            }
        });

        // set completion listener
        videoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.seekTo(0);
                mp.setPlaybackSpeed(1.0f);
                mp.start();
            }
        });


        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.stopPlayback();
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoPlayer.stopPlayback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.stopPlayback();
    }
}
