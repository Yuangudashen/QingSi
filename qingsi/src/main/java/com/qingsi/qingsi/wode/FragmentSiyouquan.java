package com.qingsi.qingsi.wode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingsi.qingsi.R;

/**
 * Created by Administrator on 2016/10/5 0005.
 */
public class FragmentSiyouquan extends Fragment {

    View fragment_siyouquan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_siyouquan = inflater.inflate(R.layout.fragment_siyouquan, container, false);

        return fragment_siyouquan;
    }
}
