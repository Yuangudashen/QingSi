package com.qingsi.qingsi.siyu;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/3.
 */

public class MessageEntity  implements Serializable {

    public static final int MESAGE_TYPE_RESEIVE = 0;
    public static final int MESAGE_TYPE_SEND = 1;
    public static final int MESAGE_TYPE_SEND_voice = 2;
    public static final int MESAGE_TYPE_receive_voice = 3;
    public static final int MESAGE_TYPE_SEND_img = 4;
    public static final int MESAGE_TYPE_receive_img = 5;


    public String name;
    public String date;

    public String text;
    public int type;

    public String voiceLength;
    public String voicePath;

    public String imgFilePath;

    public MessageEntity(String name, String date, String text, int type, String voiceLength, String voicePath) {
        this.name = name;
        this.date = date;
        this.text = text;
        this.type = type;
        this.voiceLength = voiceLength;
        this.voicePath = voicePath;
    }

    public MessageEntity() {
    }

    public MessageEntity(String name, String date, String text, int type) {
        super();
        this.name = name;
        this.date = date;
        this.text = text;
        this.type = type;
    }

    public MessageEntity(String name, String date, String text, int type, String voiceLength) {
        this.name = name;
        this.date = date;
        this.text = text;
        this.type = type;
        this.voiceLength = voiceLength;
    }

    public MessageEntity(String name, String date, String text, int type, String voiceLength, String voicePath, String imgFilePath) {
        this.name = name;
        this.date = date;
        this.text = text;
        this.type = type;
        this.voiceLength = voiceLength;
        this.voicePath = voicePath;
        this.imgFilePath = imgFilePath;
    }
}
