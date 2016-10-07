package com.qingsi.qingsi.fujin;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qingsi.qingsi.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/4.
 */
public class FujinjuhuiFragment extends Fragment implements View.OnClickListener {

    protected TextView fujinjuhuiTextView1;
    protected TextView fujinjuhuiTextView2;
    protected TextView fujinjuhuiTextView3;
    protected TextView fujinjuhuiTextView4;
    private View view;
    private ListView listView1;
    private ListView listView2;
    private ListView listView3;
    private ListView listView4;
    ArrayList<String> data = new ArrayList<>();
    private SpannableString spannableString1;
    private SpannableString spannableString2;
    private SpannableString spannableString3;
    private SpannableString spannableString4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            data.add("ss" + i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = container.inflate(getActivity(), R.layout.fujinjuhui, null);
        initView();
        init();
        return view;
    }

    private void init() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, data);
        listView1.setAdapter(adapter);
        listView2.setAdapter(adapter);
        listView3.setAdapter(adapter);
        listView4.setAdapter(adapter);
    }

    private void initView() {
        fujinjuhuiTextView1 = (TextView) view.findViewById(R.id.fujinjuhui_textView1);
        fujinjuhuiTextView1.setOnClickListener(FujinjuhuiFragment.this);
        icon1();
        fujinjuhuiTextView1.setText(spannableString1);

        fujinjuhuiTextView2 = (TextView) view.findViewById(R.id.fujinjuhui_textView2);
        fujinjuhuiTextView2.setOnClickListener(FujinjuhuiFragment.this);
        icon2();
        fujinjuhuiTextView2.setText(spannableString2);

        fujinjuhuiTextView3 = (TextView) view.findViewById(R.id.fujinjuhui_textView3);
        fujinjuhuiTextView3.setOnClickListener(FujinjuhuiFragment.this);
        icon3();
        fujinjuhuiTextView3.setText(spannableString3);

        fujinjuhuiTextView4 = (TextView) view.findViewById(R.id.fujinjuhui_textView4);
        fujinjuhuiTextView4.setOnClickListener(FujinjuhuiFragment.this);
        icon4();
        fujinjuhuiTextView4.setText(spannableString4);

        listView1 = (ListView) view.findViewById(R.id.fujinjuhui_listView1);
        listView2 = (ListView) view.findViewById(R.id.fujinjuhui_listView2);
        listView3 = (ListView) view.findViewById(R.id.fujinjuhui_listView3);
        listView4 = (ListView) view.findViewById(R.id.fujinjuhui_listView4);
    }

    private void icon1() {
        spannableString1 = new SpannableString("我要加入图片");
        Drawable drawable = getResources().getDrawable(R.drawable.jiantou2);
        drawable.setBounds(0, 0, 30, 25);
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString1.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    private void icon2() {
        spannableString2 = new SpannableString("我要发起图片");
        Drawable drawable = getResources().getDrawable(R.drawable.jiantou2);
        drawable.setBounds(0, 0, 30, 25);
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString2.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    private void icon3() {
        spannableString3 = new SpannableString("我的邀请图片");
        Drawable drawable = getResources().getDrawable(R.drawable.jiantou2);
        drawable.setBounds(0, 0, 30, 25);
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString3.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    private void icon4() {
        spannableString4 = new SpannableString("我的历史图片");
        Drawable drawable = getResources().getDrawable(R.drawable.jiantou2);
        drawable.setBounds(0, 0, 30, 25);
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString4.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }


    boolean w = true;
    boolean e = true;
    boolean r = true;
    boolean t = true;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fujinjuhui_textView1) {
            if (w) {
                listView1.setVisibility(View.VISIBLE);
                Drawable drawables1 = getResources().getDrawable(R.drawable.jiantou);
                SpannableString spannableString = new SpannableString("我要加入图片");
                drawables1.setBounds(0, 0, 30, 25);
                ImageSpan imageSpan = new ImageSpan(drawables1);
                spannableString.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                fujinjuhuiTextView1.setText(spannableString);
            } else {
                listView1.setVisibility(View.GONE);
                Drawable drawables2 = getResources().getDrawable(R.drawable.jiantou2);
                SpannableString spannableString = new SpannableString("我要加入图片");
                drawables2.setBounds(0, 0, 30, 25);
                ImageSpan imageSpan = new ImageSpan(drawables2);
                spannableString.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                fujinjuhuiTextView1.setText(spannableString);
            }
            w = !w;

        } else if (view.getId() == R.id.fujinjuhui_textView2) {
            if (e) {
                listView2.setVisibility(View.VISIBLE);
                Drawable drawabled1 = getResources().getDrawable(R.drawable.jiantou);
                SpannableString spannableString = new SpannableString("我要发起图片");
                drawabled1.setBounds(0, 0, 30, 25);
                ImageSpan imageSpan = new ImageSpan(drawabled1);
                spannableString.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                fujinjuhuiTextView2.setText(spannableString);
            } else {
                listView2.setVisibility(View.GONE);
                Drawable drawabled2 = getResources().getDrawable(R.drawable.jiantou2);
                SpannableString spannableString = new SpannableString("我要发起图片");
                drawabled2.setBounds(0, 0, 30, 25);
                ImageSpan imageSpan = new ImageSpan(drawabled2);
                spannableString.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                fujinjuhuiTextView2.setText(spannableString);
            }
            e = !e;

        } else if (view.getId() == R.id.fujinjuhui_textView3) {
            if (r) {
                listView3.setVisibility(View.VISIBLE);
                Drawable drawablef1 = getResources().getDrawable(R.drawable.jiantou);
                SpannableString spannableString = new SpannableString("我的邀请图片");
                drawablef1.setBounds(0, 0, 30, 25);
                ImageSpan imageSpan = new ImageSpan(drawablef1);
                spannableString.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                fujinjuhuiTextView3.setText(spannableString);
            } else {
                listView3.setVisibility(View.GONE);
                Drawable drawablef2 = getResources().getDrawable(R.drawable.jiantou2);
                SpannableString spannableString = new SpannableString("我的邀请图片");
                drawablef2.setBounds(0, 0, 30, 25);
                ImageSpan imageSpan = new ImageSpan(drawablef2);
                spannableString.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                fujinjuhuiTextView3.setText(spannableString);
            }
            r = !r;

        } else if (view.getId() == R.id.fujinjuhui_textView4) {
            if (t) {
                listView4.setVisibility(View.VISIBLE);
                Drawable drawableg1 = getResources().getDrawable(R.drawable.jiantou);
                SpannableString spannableString = new SpannableString("我的历史图片");
                drawableg1.setBounds(0, 0, 30, 25);
                ImageSpan imageSpan = new ImageSpan(drawableg1);
                spannableString.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                fujinjuhuiTextView4.setText(spannableString);
            } else {
                listView4.setVisibility(View.GONE);
                Drawable drawableg2 = getResources().getDrawable(R.drawable.jiantou2);
                SpannableString spannableString = new SpannableString("我的历史图片");
                drawableg2.setBounds(0, 0, 30, 25);
                ImageSpan imageSpan = new ImageSpan(drawableg2);
                spannableString.setSpan(imageSpan, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                fujinjuhuiTextView4.setText(spannableString);
            }
            t = !t;

        }
    }

}
