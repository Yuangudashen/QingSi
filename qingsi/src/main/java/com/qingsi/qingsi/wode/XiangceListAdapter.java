package com.qingsi.qingsi.wode;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingsi.qingsi.R;
import com.qingsi.qingsi.entity.XiangceListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/7 0007.
 */
public class XiangceListAdapter extends BaseAdapter{

    ArrayList<XiangceListItem> datas;

    public XiangceListAdapter(ArrayList<XiangceListItem> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.xiangce_list_item,null);
            viewHolder.xiangce_Icon = (ImageView) convertView.findViewById(R.id.xiangce_Icon);
            viewHolder.xiangce_title = (TextView) convertView.findViewById(R.id.xiangce_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        XiangceListItem xiangceListItem = datas.get(position);
        viewHolder.xiangce_title.setText(xiangceListItem.title);
        Picasso.with(parent.getContext()).load(xiangceListItem.pic_url).resize(100,200).centerCrop().into(viewHolder.xiangce_Icon);

        return convertView;
    }

    class ViewHolder{
        ImageView xiangce_Icon;
        TextView xiangce_title;
    }
}
