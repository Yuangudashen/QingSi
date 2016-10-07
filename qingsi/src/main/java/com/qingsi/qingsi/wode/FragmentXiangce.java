package com.qingsi.qingsi.wode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.qingsi.qingsi.R;
import com.qingsi.qingsi.entity.XiangceListItem;
import com.qingsi.qingsi.utils.Constants;
import com.qingsi.qingsi.utils.EmptyView;
import com.qingsi.qingsi.utils.HttpUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/6 0006.
 */
public class FragmentXiangce extends Fragment{

    private int page = 1;
    View fragment_xiangce;
    PullToRefreshGridView xiangce_list;
    XiangceListAdapter xiangceListAdapter;
    ArrayList<XiangceListItem> xiangceListItems = new ArrayList<>();
    Handler handler = new Handler();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_xiangce = inflater.inflate(R.layout.fragment_xiangce, null);

        initView();

        return fragment_xiangce;
    }

    private void initData() {
        new Thread(){
            @Override
            public void run() {
                String xiangceListUrl = Constants.XIANGCELISTURL+page;
                String xiangceListJson = HttpUtil.loadJSON(xiangceListUrl);
                parseJSON(xiangceListJson);
            }
        }.start();
    }

    private void parseJSON(String xiangceListJson) {
        JSONObject jsonObject = JSON.parseObject(xiangceListJson);


        String totalpage = (JSON.parseObject(jsonObject.getString("totalpage"))).getString("totalpage");
        Log.i("TAG","totalpage="+totalpage);

        JSONArray list = JSON.parseArray(jsonObject.getString("list"));
        for (Object o:list) {
            XiangceListItem xaingceListItem = JSON.parseObject(o.toString(),XiangceListItem.class);
            xiangceListItems.add(xaingceListItem);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                xiangceListAdapter.notifyDataSetChanged();
                xiangce_list.onRefreshComplete();
            }
        });

        xiangceListJson = null;
    }

    private void initView() {
        xiangce_list = (PullToRefreshGridView) fragment_xiangce.findViewById(R.id.xiangce_list);

        xiangce_list.setEmptyView(EmptyView.loadEmptyTextView(getActivity()));

        xiangce_list.setMode(PullToRefreshBase.Mode.BOTH);
        xiangce_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                reLoadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                loadNextPage();
            }
        });

        xiangce_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(),XiangceItemContentActivity.class);
                intent.putExtra("xiangceListItem",xiangceListItems.get(position));
                startActivity(intent);
            }
        });

        xiangceListAdapter = new XiangceListAdapter(xiangceListItems);
        xiangce_list.setAdapter(xiangceListAdapter);
        xiangceListItems.clear();
    }

    private void reLoadData() {
        xiangceListItems.clear();
        page = 1;
        initData();
    }

    private void loadNextPage() {
        page++;
        initData();
    }


}
