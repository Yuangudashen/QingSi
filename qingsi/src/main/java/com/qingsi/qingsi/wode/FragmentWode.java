package com.qingsi.qingsi.wode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.qingsi.qingsi.R;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
public class FragmentWode extends Fragment {

    View fragment_wode;
    View[] infos;
    Intent intent = null;
    // Fragment wodeFragment = null;
    LinearLayout fragment_wode_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_wode = inflater.inflate(R.layout.fragment_wode, null);

        init();
        initView();


        return fragment_wode;
    }

    private void init() {
        fragment_wode_content = (LinearLayout) fragment_wode.findViewById(R.id.fragment_wode_content);
        View fragment_wode_page = View.inflate(getActivity(), R.layout.fragment_wode_page, null);
        fragment_wode_content.removeAllViews();
        fragment_wode_content.addView(fragment_wode_page);

    }

    private void setOnClick(View view, final Fragment wodeFragment) {
//        container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.my_message_info:
//                        wodeFragment = new FragmentMyMessage();
//                        View fragment_mymessage = View.inflate(getActivity(), R.layout.fragment_mymessage, null);
//                        fragment_wode_content.removeAllViews();
//                        fragment_wode_content.addView(fragment_mymessage);
//                        break;
//                    case R.id.siyouDongtai_info:
//                        wodeFragment = new FragmentSiyouDongtai();
//                        break;
//                    case R.id.siyouQuan_info:
//                        wodeFragment = new FragmentSiyouquan();
//                        break;
//                    case R.id.qingsishi_info:
//                        wodeFragment = new FragmentQingsishi();
//                        break;
//                    case R.id.shezhi_info:
//                        wodeFragment = new FragmentShezhi();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_wode_content.removeAllViews();
                loadFragment(wodeFragment);
            }
        });
    }

    private void loadFragment(Fragment wodeFragment) {

        getChildFragmentManager().beginTransaction().replace(R.id.fragment_wode_content, wodeFragment).commit();
    }

    private void initView() {

        RelativeLayout my_message_info = (RelativeLayout) fragment_wode.findViewById(R.id.my_message_info);

        setOnClick(my_message_info,new FragmentMyMessage());

        RelativeLayout siyouDongtai_info = (RelativeLayout) fragment_wode.findViewById(R.id.siyouDongtai_info);
        setOnClick(siyouDongtai_info,new FragmentSiyouDongtai());
        RelativeLayout siyouQuan_info = (RelativeLayout) fragment_wode.findViewById(R.id.siyouQuan_info);
        setOnClick(siyouQuan_info,new FragmentSiyouquan());
        RelativeLayout qingsishi_info = (RelativeLayout) fragment_wode.findViewById(R.id.qingsishi_info);
        setOnClick(qingsishi_info,new FragmentQingsishi());
        RelativeLayout shezhi_info = (RelativeLayout) fragment_wode.findViewById(R.id.shezhi_info);
        setOnClick(shezhi_info,new FragmentShezhi());
//        infos = new View[]{my_message_info,siyouDongtai_info,siyouQuan_info,qingsishi_info,shezhi_info};

    }

}
