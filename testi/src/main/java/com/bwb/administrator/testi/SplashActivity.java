package com.bwb.administrator.testi;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }
    MediaPlayer mediaPlayer;
    private void initView() {
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.play_content);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(new HolderCallback());

        mediaPlayer = new MediaPlayer();

    }

    private class HolderCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                mediaPlayer.setDataSource("file:///android_asset/start_app.gif");
                mediaPlayer.setDisplay(holder);

                mediaPlayer.setOnPreparedListener(new PreparedListener());
                // 异步预备
                mediaPlayer.prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private class PreparedListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            // 开始播放
            mediaPlayer.start();
        }
    }
}