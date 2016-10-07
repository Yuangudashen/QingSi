package com.qingsi.qingsi.siyu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.qingsi.qingsi.R;
import com.qingsi.qingsi.base.BaseActivity;
import com.qingsi.qingsi.base.MainActivity;
import com.qingsi.qingsi.utils.FormateDateUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ChatActivity extends BaseActivity {
    private ListView listView_chat;
    private Button button_voice, button_send, button_more;
    private RelativeLayout view_bottom;
    private List<MessageEntity> list_chat;
    List<Map<String, Object>> list_gridView;
    Button button_speack;
    MediaRecorder mediaRecorder;
    RecorderDialog dialog;
    RecorderDialog2.Builder builder;
    Contact contactChat;
    EditText editText_contont;
    long startRecorderTime, endRecorderTime;
    ChatListViewAdapter chatAdapter;
    String recorderFilePath;
    String cmeraImgFilePath;
    LinearLayout linearLayout_bg;
    MediaPlayer mediaPlayer;//播放语音
    EMMessageListener msgListener;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, Menu.NONE, "思语回忆录");
        menu.add(0, 2, Menu.NONE, "修改背景");
        menu.add(0, 3, Menu.NONE, "主题颜色");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int i = 0;
        Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(this, ChatRecordActivity.class);
                startActivity(intent);
                break;
            case 2:
                Toast.makeText(this, "I的值" + i, Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    linearLayout_bg.setBackgroundColor(0xE066FF);
                } else if (i == 1) {
                    linearLayout_bg.setBackgroundColor(0xC6E2FF);
                } else if (i == 2) {
                    linearLayout_bg.setBackgroundColor(0xB452CD);
                    i = -1;
                }

                i++;
                break;
            case 3:
                // do something
                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        receiveMessage();
        initHead();
        initTitleView();
        initDatas();
        initViews();
        //底部加载
        initbottomMore();
        initbottom();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在不需要的时候移除listener，如在activity的onDestroy()时
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    private void receiveMessage() {

        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                for (int j = 0; j < messages.size(); j++) {
                   long messageTime = messages.get(j).getMsgTime();
                    String messageTimeStr = null;
                    EMMessage.Type type = messages.get(j).getType();
                    if (EMMessage.Type.TXT.equals(type)){
                        list_chat.add(new MessageEntity(contactChat.name,messageTimeStr, messages.get(j).getBody().toString(), MessageEntity.MESAGE_TYPE_RESEIVE));
                        chatAdapter.notifyDataSetChanged();
                    }else if (EMMessage.Type.IMAGE.equals(type)){
//                        list_chat.add(new MessageEntity(contactChat.name,messageTimeStr, messages.get(j).getBody().toString(), MessageEntity.MESAGE_TYPE_receive_img));
//                        chatAdapter.notifyDataSetChanged();

                    }else if(EMMessage.Type.VOICE.equals(type)){
                      //  list_chat.add(new MessageEntity(contactChat.name, FormateDateUtil.formateDateGetString(new Date()), null, MessageEntity.MESAGE_TYPE_SEND_voice, "" + null, recorderFilePath));
                        //list_chat.add(new MessageEntity(contactChat.name,messageTimeStr, messages.get(j).getBody().toString(), MessageEntity.MESAGE_TYPE_receive_img));
//                        chatAdapter.notifyDataSetChanged();
                    }
                }


            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {

                //收到透传消息
            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);

    }

    private void initTitleView() {
        ImageView img_back = (ImageView) findViewById(R.id.imageView_chat_title_back);
        ImageView img_more = (ImageView) findViewById(R.id.imageView_chat_title_more);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        this.registerForContextMenu(img_more);

    }

    private void initHead() {
        TextView text_head = (TextView) findViewById(R.id.textView_contact_name);
        Intent intent = getIntent();
        contactChat = (Contact) intent.getSerializableExtra("name");
        text_head.setText(contactChat.name);
    }

    private void initbottomMore() {
        list_gridView = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("pic", R.drawable.bottom_chat_tupian);
        map1.put("name", "图片");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("pic", R.drawable.bottom_chat_shiping);
        map2.put("name", "视屏");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("pic", R.drawable.bottom_chat_shouchang);
        map3.put("name", "收藏");
        Map<String, Object> map4 = new HashMap<>();
        map4.put("pic", R.drawable.bottom_chat_hongbao);
        map4.put("name", "红包");
        Map<String, Object> map5 = new HashMap<>();
        map5.put("pic", R.drawable.bottom_chat_weizhi);
        map5.put("name", "位置");
        Map<String, Object> map6 = new HashMap<>();
        map6.put("pic", R.drawable.bottom_chat_mingpian);
        map6.put("name", "名片");
        list_gridView.add(map1);
        list_gridView.add(map2);
        list_gridView.add(map3);
        list_gridView.add(map4);
        list_gridView.add(map5);
        list_gridView.add(map6);
        map1 = null;
        map2 = null;
        map3 = null;
        map4 = null;
        map5 = null;
        map6 = null;

        String from[] = {"pic", "name"};
        int to[] = {R.id.imageView_more_pic, R.id.textView_more_name};
        GridView gridView_more = (GridView) findViewById(R.id.gridView_bottom_more);
        SimpleAdapter adapter = new SimpleAdapter(this, list_gridView, R.layout.chat_gridview_more, from, to);
        gridView_more.setAdapter(adapter);
        gridView_more.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //拍照回传并发送
                    case 0:
                        Intent intent = new Intent();
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        cmeraImgFilePath = "/sdcard/" + UUID.randomUUID().toString() + ".jpg";
                        File file = new File(cmeraImgFilePath);
                        Uri uri = Uri.fromFile(file);
                        // 设置系统相机拍摄照片完成后图片文件的存放地址
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent, 1);
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            CmeraDialog.Builder cmeraBuilder = new CmeraDialog.Builder(this);
            Bitmap bitmap = BitmapFactory.decodeFile(cmeraImgFilePath);
            cmeraBuilder.setBitmap(bitmap);
            cmeraBuilder.setPositiveButton("发送", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    list_chat.add(new MessageEntity(contactChat.name, FormateDateUtil.formateDateGetString(new Date()), null, MessageEntity.MESAGE_TYPE_SEND_img, null, null, cmeraImgFilePath));
                    chatAdapter.notifyDataSetChanged();
                    cmeraImgFilePath = null;
                    view_bottom.setVisibility(View.GONE);
                    //imagePath为图片本地路径，false为不发送原图（默认超过100k的图片会压缩后发给对方），需要发送原图传true
                    EMMessage message = EMMessage.createImageSendMessage(cmeraImgFilePath, false, contactChat.name);
                    EMClient.getInstance().chatManager().sendMessage(message);
                }
            });

            cmeraBuilder.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });


            cmeraBuilder.create().show();


        }

    }

    private void initDatas() {
        list_chat = new ArrayList<>();
        list_chat.add(new MessageEntity("1", "12", "nihao", MessageEntity.MESAGE_TYPE_RESEIVE));
        list_chat.add(new MessageEntity("1", "12", "nihao", MessageEntity.MESAGE_TYPE_RESEIVE));
        list_chat.add(new MessageEntity("1", "12", "nihao", MessageEntity.MESAGE_TYPE_SEND));
        list_chat.add(new MessageEntity("1", "12", "", MessageEntity.MESAGE_TYPE_SEND_voice, "12"));
    }

    private void initbottom() {

        //1.得到InputMethodManager对象
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        button_more.setOnClickListener(new View.OnClickListener() {
            //得到InputMethodManager的实例
            int i = 0;

            @Override
            public void onClick(View v) {
                i++;
                if (i % 2 == 0) {
                    imm.hideSoftInputFromWindow(button_more.getWindowToken(), 0);
                    view_bottom.setVisibility(View.VISIBLE);

                } else {
                    view_bottom.setVisibility(View.GONE);
                    imm.showSoftInput(button_more, InputMethodManager.SHOW_FORCED);

                }


            }
        });
    }

    private void initViews() {
        linearLayout_bg = (LinearLayout) findViewById(R.id.activity_chat);
        listView_chat = (ListView) findViewById(R.id.listView_chat);
        button_voice = (Button) findViewById(R.id.button_voice);
        button_send = (Button) findViewById(R.id.button_send);
        button_more = (Button) findViewById(R.id.button_more);
        button_speack = (Button) findViewById(R.id.button_speack);
        view_bottom = (RelativeLayout) findViewById(R.id.relativeLayout_bottom);
        editText_contont = (EditText) findViewById(R.id.editText_chat_contont);
        pressSpeach();//录音
        playVoice();//播放
        chatAdapter = new ChatListViewAdapter(list_chat);
        listView_chat.setAdapter(chatAdapter);

    }

    private void pressSpeach() {


        button_speack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //生成唯一的一个id作为录制的文件名
                String recorderID = UUID.randomUUID().toString();
                mediaRecorder = new MediaRecorder();
                recorderFilePath = "/sdcard/" + recorderID + ".amr";
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mediaRecorder.setOutputFile(recorderFilePath);
                try {
                    mediaRecorder.prepare();// 准备录制
                    mediaRecorder.start();// 开始录制
                    //开始记录时长
                    startRecorderTime = System.currentTimeMillis();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //dialog.show();
                button_speack.setText("正在录音");
                builder.create().show();

                return true;
            }
        });
    }

    //发送文本消息
    public void send(View view) {
        String toChatUsername = contactChat.name;
        String content = editText_contont.getText().toString();
        /*//创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        EMClient.getInstance().chatManager().sendMessage(message);*/
        //获取当前时间
        Date date = new Date();
        String dateStr = FormateDateUtil.formateDateGetString(date);
        list_chat.add(new MessageEntity(contactChat.name, dateStr, content, MessageEntity.MESAGE_TYPE_SEND));
        chatAdapter.notifyDataSetChanged();
        editText_contont.setText(null);
        //隐藏键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    int i = 0;

    //发送语音
    public void sendVoice(View view) {
        final InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bottom_chat_face);
        RecorderDialog.Builder dialogBuild = new RecorderDialog.Builder(this);
        dialogBuild.setImage(bitmap);
        dialog = dialogBuild.create();
        dialog.setCanceledOnTouchOutside(true);// 点击外部区域关闭*/
        builder = new RecorderDialog2.Builder(this, R.style.Dialog);
        builder.setMessage("正在录音......");
        builder.setTitle("录音");
        builder.setPositiveButton("发送", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mediaRecorder.stop();
                // 彻底释放资源
                mediaRecorder.release();
                mediaRecorder = null;

                button_speack.setText("长按一下开始录音");
                //结束时的时间
                endRecorderTime = System.currentTimeMillis();

                int length = (int) ((endRecorderTime - startRecorderTime) / 1000);

                view_bottom.setVisibility(View.GONE);
                if (length <= 0) {
                    Toast.makeText(ChatActivity.this, "时间太短，请重新发送", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatActivity.this, "时长" + length, Toast.LENGTH_SHORT).show();
                    list_chat.add(new MessageEntity(contactChat.name, FormateDateUtil.formateDateGetString(new Date()), null, MessageEntity.MESAGE_TYPE_SEND_voice, "" + length, recorderFilePath));
                    chatAdapter.notifyDataSetChanged();
                    //filePath为语音文件路径，length为录音时间(秒)
//                    EMMessage message = EMMessage.createVoiceSendMessage(recorderFilePath, length, contactChat.name);
//                    EMClient.getInstance().chatManager().sendMessage(message);
                }
                recorderFilePath = null;

            }
        });

        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        mediaRecorder.stop();
                        // 彻底释放资源
                        mediaRecorder.release();
                        mediaRecorder = null;

                        button_speack.setText("长按一下开始录音");
                    }
                });

        if (i % 2 == 0) {
            button_speack.setVisibility(View.VISIBLE);
            imm1.hideSoftInputFromWindow(button_more.getWindowToken(), 0);
        } else {
            button_speack.setVisibility(View.GONE);
        }
        i++;
    }

    //播放语音
    private void playVoice() {
        listView_chat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ChatActivity.this, "播放语音type"+list_chat.get(position).type, Toast.LENGTH_SHORT).show();
                if (list_chat.get(position).type == 2 || list_chat.get(position).type == 3) {

                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(list_chat.get(position).voicePath);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mediaPlayer.release();
                                mediaPlayer = null;
                            }
                        });
                        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                            @Override
                            public boolean onError(MediaPlayer mp, int what, int extra) {
                                mediaPlayer.release();
                                mediaPlayer = null;
                                Toast.makeText(ChatActivity.this, "语音已经不存在", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        });
                    } catch (IOException e) {
                    }
                }
            }
        });
    }


}
