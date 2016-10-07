package com.qingsi.qingsi.siyu;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/4.
 */

public class Contact implements Serializable {
    public String name;

    public Contact() {
    }

    public Contact(String name) {
        this.name = name;
    }
}
