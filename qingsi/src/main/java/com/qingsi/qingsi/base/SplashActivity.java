package com.qingsi.qingsi.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qingsi.qingsi.R;

public class SplashActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        SharedPreferences sp = getSharedPreferences("loadflags", Context.MODE_PRIVATE);
        boolean hasGuided = sp.getBoolean("hasGuided", false);

        if (hasGuided) {
            // 已经经过引导，即不是第一次启动
            intent = new Intent(this, LoginActivity.class);
        } else {
            // 第一次启动
            intent = new Intent(this, NavigationActivity.class);
        }
        new Thread(){
            public void run() {
                try {
                    Thread.sleep(3000);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    // MediaPlayer mediaPlayer;

    //    private class HolderCallback implements SurfaceHolder.Callback {
//
//        @Override
//        public void surfaceCreated(SurfaceHolder holder) {
//            try {
//                mediaPlayer.setDataSource("file:///android_asset/start_app.gif");
//                mediaPlayer.setDisplay(holder);
//
//                mediaPlayer.setOnPreparedListener(new PreparedListener());
//                // 异步预备
//                mediaPlayer.prepareAsync();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//        }
//
//        @Override
//        public void surfaceDestroyed(SurfaceHolder holder) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }
//
//    private class PreparedListener implements MediaPlayer.OnPreparedListener {
//
//        @Override
//        public void onPrepared(MediaPlayer mp) {
//            // 开始播放
//            mediaPlayer.start();
//        }
//    }
}
