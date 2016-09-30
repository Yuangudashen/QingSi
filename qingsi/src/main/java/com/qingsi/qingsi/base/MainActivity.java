package com.qingsi.qingsi.base;

import android.content.Context;
import android.os.Bundle;

import com.qingsi.qingsi.R;

/**
 * 工作Activity，其他四个页面以Fragment形式加载
 */
public class MainActivity extends BaseActivity {

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
