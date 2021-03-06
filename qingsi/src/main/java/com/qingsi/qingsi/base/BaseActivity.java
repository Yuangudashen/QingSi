package com.qingsi.qingsi.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    public static List<Activity> list_activitys = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_base_activity);
//        initSystemBarTint(true, );
//        setTranslucentStatus(true);
        list_activitys.add(this);

    }

    /**
     * 使用沉浸式通知栏要添加项目依赖及以下两个方法，并在Activity跟布局添加android:fitsSystemWindows="true"
     */
    public void initSystemBarTint(boolean on, int res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(on);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(on);
            tintManager.setStatusBarTintResource(res);
        }
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow(); WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
