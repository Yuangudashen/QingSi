package com.qingsi.qingsi.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qingsi.qingsi.R;
import com.qingsi.qingsi.fujin.FragmentFujin;
import com.qingsi.qingsi.siyu.FragmentSiyu;
import com.qingsi.qingsi.tuijian.FragmentTuijian;
import com.qingsi.qingsi.wode.FragmentWode;

/**
 * 工作Activity，其他四个页面以Fragment形式加载
 */
public class MainActivity extends BaseActivity {

    private Context context;
    private Fragment fragment = null;
    TextView page_title;
    RelativeLayout page_head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        page_head = (RelativeLayout) findViewById(R.id.page_head);
        page_title = (TextView) findViewById(R.id.page_title);


        fragment = new FragmentTuijian();
        load(fragment);
        page_title.setText("推荐");
        // page_head.setVisibility(View.GONE);
        RadioButton radioButton_tuijian = (RadioButton) findViewById(R.id.radioButton_tuijian);
        radioButton_tuijian.setChecked(true);
    }

    public void changeFragment(View view) {
        switch (view.getId()) {
            case R.id.radioButton_tuijian:
                fragment = new FragmentTuijian();
                page_title.setText("推荐");
                // page_head.setVisibility(View.GONE);
                break;
            case R.id.radioButton_siyu:
                fragment = new FragmentSiyu();
                page_head.setVisibility(View.VISIBLE);
                page_title.setText("思语");
                break;
            case R.id.radioButton_fujin:
                fragment = new FragmentFujin();
                page_head.setVisibility(View.VISIBLE);
                page_title.setText("附近");
                break;
            case R.id.radioButton_wode:
                fragment = new FragmentWode();
                page_head.setVisibility(View.VISIBLE);
                page_title.setText("我的");
                break;
            default:
                break;
        }
        load(fragment);
    }

    private void load(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,fragment).commit();
    }

}