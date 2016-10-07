package com.qingsi.qingsi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/7 0007.
 */
public class XiangceListItem implements Serializable{


    public String title;
    public String pic_url;
    public String detail_url;

    public XiangceListItem() {
    }

    public XiangceListItem(String title, String pic_url, String detail_url) {
        this.title = title;
        this.pic_url = pic_url;
        this.detail_url = detail_url;
    }
}
