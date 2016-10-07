package com.qingsi.qingsi.fujin;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.baidu.mapapi.search.poi.PoiSearch;
import com.qingsi.qingsi.R;

/**
 * Created by Administrator on 2016/10/5.
 */
public class leidaFragment extends Fragment {
    private PoiSearch poiSearch;
    //private LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // locationService = new LocationService(getContext());
       // mVibrator =(Vibrator)getContext().getSystemService(Service.VIBRATOR_SERVICE);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view = container.inflate(getActivity(), R.layout.leida,null);
        WebView webView= (WebView) view.findViewById(R.id.fujin_leida);
        webView.loadUrl("file:///android_asset/sousuo.gif");
        return view;
    }

}
