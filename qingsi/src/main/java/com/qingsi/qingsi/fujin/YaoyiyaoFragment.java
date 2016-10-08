package com.qingsi.qingsi.fujin;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qingsi.qingsi.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */
public class YaoyiyaoFragment extends Fragment implements SensorEventListener{
    Map<Integer, Integer> datas = new HashMap<>();
    private SoundPool sp;
    private SensorManager sensorManager = null;
    private Vibrator vibrator = null;
    private LinearLayout topLayout, bottomLayout;
    private ImageView topLineIv, bottomLineIv;
    private boolean isShake = false;
    private View view;


    @Override
    public void onDestroy() {
        super.onDestroy();
      sp.stop(AudioManager.STREAM_MUSIC);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = container.inflate(getActivity(), R.layout.yaoyiyao, null);

         //初始化播放器--soundPool主要用于播放短声音，比mediaplayer耗费的资源少
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        datas.put(1, sp.load(getContext(), R.raw.yaoyiyao, 1));

        topLayout = (LinearLayout) view.findViewById(R.id.shake_top_layout);
        topLineIv = (ImageView) view.findViewById(R.id.shake_top_line);
        bottomLayout = (LinearLayout) view.findViewById(R.id.shake_bottom_layout);
        bottomLineIv = (ImageView) view.findViewById(R.id.shake_bottom_line);
        topLineIv.setVisibility(View.GONE);
        bottomLineIv.setVisibility(View.GONE);
//传感器管理器
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService(Service.VIBRATOR_SERVICE);
        /*android.os.Handler handler=new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println("----ss"+msg.what);
                if (msg.what==0){



                }
            }
        };*/
        init();
        return view;

    }
    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        int sensorType = event.sensor.getType();
        // values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            if ((Math.abs(values[0]) > 17 || Math.abs(values[1]) > 17 || Math
                    .abs(values[2]) > 17) && !isShake) {
                isShake = true;
                new Thread() {
                    public void run() {
                        try {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    // 摇动手机后，再伴随震动提示~~
                                    vibrator.vibrate(300);
                                    topLineIv.setVisibility(View.VISIBLE);
                                    bottomLineIv.setVisibility(View.VISIBLE);
                                    startAnimation(false);
                                }
                            });
                            Thread.sleep(500);
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    // 摇动手机后，再伴随震动提示~~
                                    vibrator.vibrate(300);
                                }
                            });
                            Thread.sleep(500);
                            getActivity().runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    isShake = false;
                                    startAnimation(true);
                                }
                            });
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    };
                }.start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    private void startAnimation(boolean isBack) {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        float topFromYValue;
        float topToYValue;
        float bottomFromYValue;
        float bottomToYValue;
        if (isBack) {
            topFromYValue = -0.5f;
            topToYValue = 0;
            bottomFromYValue = 0.5f;
            bottomToYValue = 0;
        } else {
            topFromYValue = 0;
            topToYValue = -0.5f;
            bottomFromYValue = 0;
            bottomToYValue = 0.5f;
        }
        TranslateAnimation topAnimation = new TranslateAnimation(type, 0, type,
                0, type, topFromYValue, type, topToYValue);
        topAnimation.setDuration(200);
        topAnimation.setFillAfter(true);
        TranslateAnimation bottomAnimation = new TranslateAnimation(type, 0,
                type, 0, type, bottomFromYValue, type, bottomToYValue);
        bottomAnimation.setDuration(200);
        bottomAnimation.setFillAfter(true);
        if (isBack) {
            bottomAnimation
                    .setAnimationListener(new TranslateAnimation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            // TODO Auto-generated method stub
                            topLineIv.setVisibility(View.GONE);
                            bottomLineIv.setVisibility(View.GONE);
                        }
                    });
        }
        bottomLayout.startAnimation(bottomAnimation);
        topLayout.startAnimation(topAnimation);
    }

    private void init() {


        //获取当前手机所有的传感器并存放在list集合里
        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor a : list) {
        }
        //获取加速度传感器
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //监听器
        sensorManager.registerListener(new S(), sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    class S implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            //获取传感器产生的所有数据
            float[] values = sensorEvent.values;
            if (values[0] > 17 || values[1] > 17 || values[2] > 17) {
                //System.out.println("手机摇动了");
                sp.play(datas.get(1), 1, 1, 0, 1, 1);

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
}
