package com.qingsi.qingsi.wode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qingsi.qingsi.R;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
public class FragmentWode extends Fragment {

    View fragment_wode;
    View[] infos;
    Intent intent = null;
    Fragment wodeFragment = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_wode = inflater.inflate(R.layout.fragment_wode, container,false);

        init();
        initView();
        setOnClick();

        return fragment_wode;
    }

    private void init() {
        View fragment_wode_layout = fragment_wode.findViewById(R.id.fragment_wode_layout);
        // TODO 下午
    }

    private void setOnClick() {
        for (int i = 0;i<infos.length;i++) {
            View info = infos[i];
            switch (i){
                case 0:
                    wodeFragment = new FragmentMyMessage();
                    intent = new Intent(getActivity(),MyMessageActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(),MyMessageActivity.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(),MyMessageActivity.class);
                    break;
                case 3:
                    intent = new Intent(getActivity(),MyMessageActivity.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(),MyMessageActivity.class);
                    break;

                default:
                    break;
            }

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loagFragment(wodeFragment);
                }
            });
        }
    }

    private void loagFragment(Fragment wodeFragment) {
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_wode_layout,wodeFragment).commit();
    }

    private void initView() {

        RelativeLayout my_message_info = (RelativeLayout) fragment_wode.findViewById(R.id.my_message_info);

        RelativeLayout siyouDongtai_info = (RelativeLayout) fragment_wode.findViewById(R.id.siyouDongtai_info);

        RelativeLayout siyouQuan_info = (RelativeLayout) fragment_wode.findViewById(R.id.siyouQuan_info);

        RelativeLayout qingsishi_info = (RelativeLayout) fragment_wode.findViewById(R.id.qingsishi_info);

        RelativeLayout shezhi_info = (RelativeLayout) fragment_wode.findViewById(R.id.shezhi_info);

        infos = new View[]{my_message_info,siyouDongtai_info,siyouQuan_info,qingsishi_info,shezhi_info};

    }

}
