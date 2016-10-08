package com.qingsi.qingsi.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qingsi.qingsi.R;
import com.qingsi.qingsi.fujin.FragmentFujin;
import com.qingsi.qingsi.siyu.FragmentSiyu;
import com.qingsi.qingsi.tuijian.FragmentTuijian;
import com.qingsi.qingsi.wode.FragmentWode;

import java.util.List;

/**
 * 工作Activity，其他四个页面以Fragment形式加载
 */
public class MainActivity extends BaseActivity {

    private Context context;
    private Fragment fragment = null;
    TextView page_title;
    RelativeLayout page_head;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Activity> list_activitys = BaseActivity.list_activitys;
        for (int i = 0; i < list_activitys.size()-1; i++) {
            list_activitys.get(i).finish();

        }
        initView();

    }

    private void initView() {
        page_head = (RelativeLayout) findViewById(R.id.page_head);
        page_title = (TextView) findViewById(R.id.page_title);


        fragment = new FragmentTuijian();
        load(fragment);
        page_title.setText("推荐");

        RadioButton radioButton_tuijian = (RadioButton) findViewById(R.id.radioButton_tuijian);
        radioButton_tuijian.setChecked(true);

    }

    public void changeFragment(View view) {
        switch (view.getId()) {
            case R.id.radioButton_tuijian:
                fragment = new FragmentTuijian();
                page_title.setText("推荐");
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