package com.qingsi.qingsi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/4.
 */

public class Contact implements Serializable {
    public String name;
    public String messageNumbers;
    public String imgHeadPath;
    public Contact() {
    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, String imgHeadPath) {
        this.name = name;
        this.imgHeadPath = imgHeadPath;
    }
}
