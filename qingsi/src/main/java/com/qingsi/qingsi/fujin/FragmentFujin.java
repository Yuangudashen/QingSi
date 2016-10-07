package com.qingsi.qingsi.fujin;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingsi.qingsi.R;
import com.qingsi.qingsi.tuijian.FragmentTuijian;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
public class FragmentFujin extends Fragment implements View.OnClickListener {


    protected FrameLayout fujinFrameLayout1;
    private TextView textView1;
    private TextView textView2;
    private View view;
    private Button button1;
    private Button button2;
    private LinearLayout layout;
    ArrayList<Fragment> datas_fragment = new ArrayList<>();
    private ViewPager viewPager;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment0 = new leidaFragment();
        fragment1 = new YaoyiyaoFragment();
        fragment2 = new FujinjuhuiFragment();
        datas_fragment.add(fragment0);
        datas_fragment.add(fragment1);
        datas_fragment.add(fragment2);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dibushensuo, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new A(getChildFragmentManager()));
        viewPager.setCurrentItem(0);
        /*if (viewPager.getCurrentItem()==1){
            Message a= Message.obtain();
            a.what=0;
        }*/
        initView();
        choose();
        return view;

    }
    class A extends FragmentPagerAdapter {

        public A(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return datas_fragment.get(arg0);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return datas_fragment.size();
        }



    }

    private void choose() {
        //给摇一摇加监听
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView1.setBackgroundColor(Color.parseColor("#A24CCB"));
                button1.setBackgroundColor(Color.parseColor("#A24CCB"));
                textView2.setBackgroundColor(Color.parseColor("#A475C5"));
                button2.setBackgroundColor(Color.parseColor("#A475C5"));
                viewPager.setCurrentItem(1);
            }
        });
        //给附近聚会加监听
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setBackgroundColor(Color.parseColor("#A24CCB"));
                button2.setBackgroundColor(Color.parseColor("#A24CCB"));
                textView1.setBackgroundColor(Color.parseColor("#A475C5"));
                button1.setBackgroundColor(Color.parseColor("#A475C5"));
                viewPager.setCurrentItem(2);

            }
        });


    }

    private void initView() {
        textView1 = (TextView) view.findViewById(R.id.fujin_textView1);
        textView1.setOnClickListener(FragmentFujin.this);
        textView2 = (TextView) view.findViewById(R.id.fujin_textView2);
        textView2.setOnClickListener(FragmentFujin.this);
        button1 = (Button) view.findViewById(R.id.fujin_button1);
        button1.setOnClickListener(FragmentFujin.this);
        button2 = (Button) view.findViewById(R.id.fujin_button2);
        button2.setOnClickListener(FragmentFujin.this);
        layout = (LinearLayout) view.findViewById(R.id.linearLayout2);
    }

    boolean a = true;

    // boolean b=true;
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fujin_button1) {
            if (a) {
                layout.setVisibility(View.VISIBLE);
                textView1.setBackgroundColor(Color.parseColor("#A475C5"));
                textView2.setBackgroundColor(Color.parseColor("#A475C5"));
            } else {
                layout.setVisibility(View.GONE);
                button1.setBackgroundColor(Color.parseColor("#A475C5"));
                button2.setBackgroundColor(Color.parseColor("#A475C5"));
            }
            a = !a;
        } else if (view.getId() == R.id.fujin_button2) {
            if (a) {
                layout.setVisibility(View.VISIBLE);
                textView1.setBackgroundColor(Color.parseColor("#A475C5"));
                textView2.setBackgroundColor(Color.parseColor("#A475C5"));
            } else {
                layout.setVisibility(View.GONE);
                button1.setBackgroundColor(Color.parseColor("#A475C5"));
                button2.setBackgroundColor(Color.parseColor("#A475C5"));

            }
            a = !a;
        } /*else if (view.getId() == R.id.fujin_textView1) {
              viewPager.setCurrentItem(0);
        } else if (view.getId() == R.id.fujin_textView2) {
            viewPager.setCurrentItem(1);
        }*/
    }


}
