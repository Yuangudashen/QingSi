package com.qingsi.qingsi.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/7 0007.
 */
public class EmptyView {

    public static View loadEmptyTextView(Context context) {
        TextView emptyView = new TextView(context);
        emptyView.setText("请稍后...");
        emptyView.setGravity(Gravity.CENTER);
        return emptyView;
    }

}
