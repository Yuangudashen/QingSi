package com.qingsi.qingsi.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.qingsi.qingsi.R;

import java.util.List;

/**
 * 登录Aactivity
 */
public class LoginActivity extends BaseActivity {

    EditText editText_userName;
    EditText editText_passWord;
    public static List<String> usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();


    }

    private void initView() {
        editText_userName = (EditText) findViewById(R.id.editText_userName);
        editText_passWord = (EditText) findViewById(R.id.editText_passWord);
    }

    /**
     * 注册
     * @param view
     */
    public void signin(View view){
        final String userName = editText_userName.getText().toString();
        final String passWord = editText_passWord.getText().toString();

        if(userName !=null && !"".equals(userName)&&passWord!=null&&!"".equals(passWord)) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(userName, passWord);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "注册成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }else{
            Toast.makeText(getBaseContext(),"用户名或密码不能为空！！",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 登录
     * @param view
     */
    public void login(View view){
        final String userName = editText_userName.getText().toString();
        final String passWord = editText_passWord.getText().toString();

        if(userName !=null && !"".equals(userName)&&passWord!=null&&!"".equals(passWord)) {

            EMClient.getInstance().login(userName, passWord, new EMCallBack() {
                @Override
                public void onSuccess() {
                    Log.i("TAG", "登陆成功！" + "----onSuccess:" + userName);
                    try {
                        usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onError(int i, String s) {
                    Log.i("TAG", "登陆失败！" + "----onError:" + userName + ", i: " + i + ",s: " + s);
                }

                @Override
                public void onProgress(int i, String s) {
                    Log.i("TAG", "----onProgress:i: " + i + ",s: " + s);
                }
            });
        }else{
            Toast.makeText(getBaseContext(),"用户名或密码不能为空！！",Toast.LENGTH_SHORT).show();
        }
    }


}
