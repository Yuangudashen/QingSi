package com.qingsi.qingsi.siyu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.qingsi.qingsi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
public class FragmentSiyu extends Fragment {
    List<Contact> list_contact, list_search, list_show;
    List<String> list_searchName;
    View view_siyu;
    View popupWindow_siyu;
    PopupWindow popupWindow;
    ContactListViewAdapter adapter;
    int itemcount;//记录当前点击的item索引

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view_siyu = inflater.inflate(R.layout.fragment_siyu_contact, null);
        initPopupWindow();

        initDatas();
        initViews();
        //搜索联系人的实现
        initsearchData();
        seachViewImplent();
        return view_siyu;
    }



    private void initsearchData() {
        list_searchName = new ArrayList<>();
        for (int i = 0; i < list_contact.size(); i++) {
            list_searchName.add(list_contact.get(i).name);
        }
    }

    private void seachViewImplent() {
        //1.得到InputMethodManager对象
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        final SearchView searchView_contact = (SearchView) view_siyu.findViewById(R.id.searchView_contact);
        searchView_contact.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                list_show.clear();
                list_search.clear();
                for (int i = 0; i < list_searchName.size(); i++) {
                    if (query.equals(list_searchName.get(i))) {
                        list_search.add(list_contact.get(i));
                    }
                }
                list_show.addAll(list_search);
                adapter.notifyDataSetChanged();
                imm.hideSoftInputFromWindow(searchView_contact.getWindowToken(), 0);
                return true;
            }

            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    private void initPopupWindow() {
        popupWindow_siyu = view_siyu.inflate(getContext(), R.layout.siyu_popupwindow, null);
        popupWindow = new PopupWindow(popupWindow_siyu);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 允许点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style); // 设置动画
        TextView textView_delect = (TextView) popupWindow_siyu.findViewById(R.id.textView_popup_delect);
        TextView textView_attention = (TextView) popupWindow_siyu.findViewById(R.id.textView_popup_attention);
        //删除好友
        textView_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_show.remove(list_contact.get(itemcount));
                list_contact.remove(list_contact.get(itemcount));
                adapter.notifyDataSetChanged();
                popupWindow.dismiss();
            }
        });

        textView_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                Toast.makeText(getContext(), "关注"+list_show.get(itemcount).name+"成功", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getContacts() {
        try {
            List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
            for (int i = 0; i < usernames.size(); i++) {
                list_contact.add(new Contact(usernames.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //  模拟数据
    private void initDatas() {
        list_contact = new ArrayList<>();
        list_search = new ArrayList<>();
        list_show = new ArrayList<>();
        list_contact.add(new Contact("小明"));
        list_contact.add(new Contact("小李"));
        list_contact.add(new Contact("王二"));
        getContacts();
        list_show.addAll(list_contact);
    }

    private void initViews() {
        final ListView listView_contact = (ListView) view_siyu.findViewById(R.id.listView_contact);
        Button button_contact1 = (Button) view_siyu.findViewById(R.id.button_contact1);
        button_contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list_show.clear();
                list_show.addAll(list_contact);
                adapter.notifyDataSetChanged();
            }
        });
        adapter = new ContactListViewAdapter(list_show);
        listView_contact.setAdapter(adapter);
        listView_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemcount = position;
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("name", list_contact.get(position));
                startActivity(intent);
            }
        });

        listView_contact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int popupWidth = view.getMeasuredWidth();
                int popupHeight = view.getMeasuredHeight();
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                Toast.makeText(getContext(), "location" + location[0], Toast.LENGTH_SHORT).show();
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, (location[0] + view.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight + 25);
                return true;
            }
        });
    }


}
