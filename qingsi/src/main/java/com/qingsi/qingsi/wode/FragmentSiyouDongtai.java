package com.qingsi.qingsi.wode;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.qingsi.qingsi.R;

/**
 * Created by Administrator on 2016/10/5 0005.
 */
public class FragmentSiyouDongtai extends Fragment{

    View fragment_siyoudongtai;

    FrameLayout fragment_siyoudongtai_content;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_siyoudongtai = inflater.inflate(R.layout.fragment_siyoudongtai,null);

        init();
        initView();

        return fragment_siyoudongtai;
    }

    private void init() {
        fragment_siyoudongtai_content = (FrameLayout) fragment_siyoudongtai.findViewById(R.id.fragment_siyoudongtai_content);
        fragment_siyoudongtai_content.removeAllViews();
    }

    private void initView() {
        RadioButton radioButton_xiangce = (RadioButton) fragment_siyoudongtai.findViewById(R.id.radioButton_xiangce);
        loadFragment(new FragmentXiangce());
        radioButton_xiangce.setChecked(true);
        setOnClick(radioButton_xiangce,new FragmentXiangce());

        RadioButton radioButton_sisi = (RadioButton) fragment_siyoudongtai.findViewById(R.id.radioButton_sisi);
        setOnClick(radioButton_sisi,new FragmentSisi());

        RadioButton radioButton_lianxiwo = (RadioButton) fragment_siyoudongtai.findViewById(R.id.radioButton_lianxiwo);
        setOnClick(radioButton_lianxiwo,new FragmentLianxiwo());
    }

    private void setOnClick(RadioButton radioButton, final Fragment siyoudongtaiFragment) {
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_siyoudongtai_content.removeAllViews();
                loadFragment(siyoudongtaiFragment);
            }
        });
    }

    private void loadFragment(Fragment siyoudongtaiFragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_wode_content,siyoudongtaiFragment).commit();
    }

}
