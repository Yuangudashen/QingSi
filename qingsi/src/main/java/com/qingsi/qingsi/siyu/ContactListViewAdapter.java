package com.qingsi.qingsi.siyu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingsi.qingsi.R;
import com.qingsi.qingsi.entity.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/10/4.
 */

public class ContactListViewAdapter extends BaseAdapter {
    List<Contact> list_contact;

    public ContactListViewAdapter(List<Contact> list_contact) {
        this.list_contact = list_contact;
    }

    @Override
    public int getCount() {
        return list_contact.size();
    }

    @Override
    public Object getItem(int position) {
        return list_contact.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 vh1;
        if (convertView == null){
            vh1 = new ViewHolder1();
            convertView = View.inflate(parent.getContext(), R.layout.contact_item,null);
            vh1.textView_name = (TextView) convertView.findViewById(R.id.textView_contact_item);
            vh1.img_contact = (ImageView) convertView.findViewById(R.id.imageView_contact_item);

            convertView.setTag(vh1);
        }else{
            vh1 = (ViewHolder1) convertView.getTag();
        }
        vh1.textView_name.setText(list_contact.get(position).name);
        Picasso.with(parent.getContext())
                .load(list_contact.get(position).imgHeadPath)
                .resize(50,50)
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(vh1.img_contact);
        return convertView;
    }
    class ViewHolder1{
        TextView textView_name;
        ImageView img_contact;
    }
}
