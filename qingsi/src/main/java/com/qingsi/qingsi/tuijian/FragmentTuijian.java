package com.qingsi.qingsi.tuijian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qingsi.qingsi.R;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
public class FragmentTuijian extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment_tuijian = inflater.inflate(R.layout.fragment_tuijian,null);
        WebView fragement_tuijian_WebView = (WebView) fragment_tuijian.findViewById(R.id.fragement_tuijian_WebView);
        WebSettings webSettings = fragement_tuijian_WebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        fragement_tuijian_WebView.loadUrl("http://192.168.0.57/qingsi/index.html");
        fragement_tuijian_WebView.setWebViewClient(new WebViewClient());
        return fragment_tuijian;
    }
}
