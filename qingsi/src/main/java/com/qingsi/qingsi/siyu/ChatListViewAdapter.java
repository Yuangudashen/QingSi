package com.qingsi.qingsi.siyu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingsi.qingsi.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/10/3.
 */

public class ChatListViewAdapter extends BaseAdapter {
    List<MessageEntity> list_message;

    public ChatListViewAdapter(List<MessageEntity> list_message) {
        this.list_message = list_message;
    }

    @Override
    public int getCount() {
        return list_message.size();
    }

    @Override
    public Object getItem(int position) {
        return list_message.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        int type = list_message.get(position).type;
        if (convertView == null){
            vh = new ViewHolder();
            if (type == MessageEntity.MESAGE_TYPE_SEND){
               convertView = View.inflate(parent.getContext(), R.layout.chat_item_send,null);
                vh.content_send = (TextView) convertView.findViewById(R.id.textView_content_send);
                vh.img_send = (ImageView) convertView.findViewById(R.id.imageView_chat_send);
                vh.time_send = (TextView) convertView.findViewById(R.id.textView_time_send);
            }else if(type == MessageEntity.MESAGE_TYPE_RESEIVE){
                convertView = View.inflate(parent.getContext(), R.layout.chat_item_receive,null);
                vh.content_receive = (TextView) convertView.findViewById(R.id.textView_content_receive);
                vh.img_receive = (ImageView) convertView.findViewById(R.id.imageView_chat_receive);
                vh.time_receive = (TextView) convertView.findViewById(R.id.textView_time_receive);
            }else if (type == MessageEntity.MESAGE_TYPE_SEND_voice){
                convertView = View.inflate(parent.getContext(), R.layout.chat_item_send_voice,null);
                vh.time_send_voice = (TextView) convertView.findViewById(R.id.textView_time_send_voice);
                vh.length_send_voice = (TextView) convertView.findViewById(R.id.textView_chat_send_voice_length);
            }else if(type == MessageEntity.MESAGE_TYPE_receive_voice){
                convertView = View.inflate(parent.getContext(), R.layout.chat_item_receive_voice,null);
                vh.time_receive_voice = (TextView) convertView.findViewById(R.id.textView_time_receive_voice);
                vh.length_receive_voice = (TextView) convertView.findViewById(R.id.textView_chat_receive_voice_length);
            } else if (type == MessageEntity.MESAGE_TYPE_SEND_img){
                convertView = View.inflate(parent.getContext(), R.layout.chat_item_send_img,null);
                vh.time_send_img = (TextView) convertView.findViewById(R.id.textView_time_send_img);

                vh.img_send_img = (ImageView) convertView.findViewById(R.id.imageView_img_send);

            }else if (type == MessageEntity.MESAGE_TYPE_receive_img){
                vh.time_receive_img = (TextView) convertView.findViewById(R.id.textView_time_receive_img);
                vh.img_receive_img = (ImageView) convertView.findViewById(R.id.imageView_img_receive);

            }
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        if (type == MessageEntity.MESAGE_TYPE_SEND){
            vh.time_send.setText(list_message.get(position).date);
            //picasso
            vh.content_send.setText(list_message.get(position).text);

        }else if (type == MessageEntity.MESAGE_TYPE_RESEIVE){
            vh.time_receive.setText(list_message.get(position).date);
            //picasso
            vh.content_receive.setText(list_message.get(position).text);
        }else if(type == MessageEntity.MESAGE_TYPE_SEND_voice){
            vh.time_send_voice.setText(list_message.get(position).date);
            vh.length_send_voice.setText(list_message.get(position).voiceLength);
        }else if (type == MessageEntity.MESAGE_TYPE_receive_voice){
            vh.time_receive_voice.setText(list_message.get(position).date);
            vh.length_receive_voice.setText(list_message.get(position).voiceLength);
        }else if (type == MessageEntity.MESAGE_TYPE_SEND_img){
            System.out.println("----adapter"+list_message.get(position).imgFilePath);
            vh.time_send_img.setText(list_message.get(position).date);
            /*Picasso.with(parent.getContext())
                    .load(list_message.get(position).imgFilePath)
                    .into(vh.img_send_img);*/
            Picasso.with(parent.getContext()).load(new File(list_message.get(position).imgFilePath))
                    .resize(250,300)
                    .centerCrop()
                    .into(vh.img_send_img);
        }else if(type == MessageEntity.MESAGE_TYPE_receive_img){
            vh.time_send_img.setText(list_message.get(position).date);
        }
        return convertView;
    }


    class ViewHolder{
        //发送文本
        ImageView img_send;
        TextView time_send;
        TextView content_send;

        ImageView img_receive;
        TextView time_receive;
        TextView content_receive;

        //发送图片
        ImageView img_send_img_pic;
        TextView time_send_img;
        ImageView img_send_img;

        ImageView img_receive_img_pic;
        TextView time_receive_img;
        ImageView img_receive_img;


        //发送语音
        ImageView img_send_voice_pic;
        TextView time_send_voice;
        ImageView img_send_voice;
        TextView length_send_voice;

        ImageView img_receive_voice_pic;
        TextView time_receive_voice;
        ImageView img_receive_voice;
        TextView length_receive_voice;


    }
}
