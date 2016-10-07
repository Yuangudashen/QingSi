package com.qingsi.qingsi.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qingsi.qingsi.R;
import com.qingsi.qingsi.adapter.AdapterPager;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {

    ArrayList<View> views = new ArrayList<View>();
    ImageView[] points;

    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        initData();
        initPoints();
        initView();
    }

    public void jumpToMain(View view){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void initView() {
        ViewPager viewPager_guid = (ViewPager) findViewById(R.id.viewPager_navigation);
        viewPager_guid.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == current){
                    points[position].setImageResource(android.R.drawable.presence_online);
                } else {
                    points[position].setImageResource(android.R.drawable.presence_online);
                    points[current].setImageResource(android.R.drawable.presence_invisible);
                    current = position;
                }

                if(position == views.size()-1){
                    SharedPreferences sharedPreference = getSharedPreferences("loadflags", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreference.edit();
                    editor.putBoolean("hasGuided",true).commit();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager_guid.setAdapter(new AdapterPager(this,views));
    }

    private void initPoints() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.buttom_points);
        int pointCount = ll.getChildCount();
        points = new ImageView[pointCount];
        for (int i = 0; i < pointCount; i++) {
            ImageView point = (ImageView) ll.getChildAt(i);
            points[i] = point;
        }
    }

    private void initData() {
        ImageView guid_1=new ImageView(getBaseContext());
        ImageView guid_2=new ImageView(getBaseContext());

        guid_1.setImageResource(R.drawable.navigation_1);
        guid_2.setImageResource(R.drawable.navigation_2);

        guid_1.setScaleType(ImageView.ScaleType.FIT_XY);
        guid_2.setScaleType(ImageView.ScaleType.FIT_XY);

        View guid_last = View.inflate(getBaseContext(),R.layout.guid_last,null);

        views.add(guid_1);
        views.add(guid_2);
        views.add(guid_last);
    }
}