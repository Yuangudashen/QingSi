package com.qingsi.qingsi.base;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

/**
 * Created by Administrator on 2016/9/30.
 */
public class InitAapplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //SDKInitializer.initialize(getApplicationContext());
        // BMapManager manager= new BMapManager(getApplicationContext());

        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);

        EMClient.getInstance().init(getApplicationContext(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }

}
