package com.qingsi.qingsi.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class HttpUtil {

    private static final int INT = 5000;

    // 根据Url或取JSON
    public static String loadJSON(String url){
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.setConnectTimeout(INT);
            InputStream in = conn.getInputStream();
            byte[] bs = new byte[1024*8];
            int count = 0;
            StringBuilder stringBuilder = new StringBuilder();
            while ((count = in.read(bs))!=-1){
                stringBuilder.append(new String(bs,0,count,"utf-8"));
            }
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 提交
    public static int submitComment(String url, String aid, String commentContent){
        int responseCode = 0;
        try {
            URL urlU = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlU.openConnection();
            conn.setConnectTimeout(INT);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            // 发送数据的类型
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("aid="+aid+"&msg="+ URLEncoder.encode(commentContent,"UTF-8"));

            OutputStream out = conn.getOutputStream();
            out.write(strBuilder.toString().getBytes());
            out.flush();
            out.close();
            conn.getInputStream();
            responseCode = conn.getResponseCode();
            return conn.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseCode;
    }
}
