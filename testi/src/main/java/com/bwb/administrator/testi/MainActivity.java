package com.bwb.administrator.testi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    int scale = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GifSurfaceView play_content = (GifSurfaceView) findViewById(R.id.play_content);
        play_content.setPath("file:///android_asset/start_app.gif");
        play_content.setZoom(scale);


    }
}
