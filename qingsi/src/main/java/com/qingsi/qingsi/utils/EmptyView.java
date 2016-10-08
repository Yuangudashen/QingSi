package com.qingsi.qingsi.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.qingsi.qingsi.R;

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

    public static View loadLoadingView(Context context) {
        View loadingView = View.inflate(context, R.layout.loading_view, null);
        WebView webView = (WebView) loadingView.findViewById(R.id.loading_gif);
        webView.canGoBack();
        webView.loadUrl("file:///android_asset/loading.gif");
        return loadingView;
    }

}
