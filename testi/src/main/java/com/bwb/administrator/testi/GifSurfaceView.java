package com.bwb.administrator.testi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;


public class GifSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    //gif图片路径
    private String path;
    private Movie movie;
    //执行动画
    private Handler handler;
    //放大倍数
    private int zoom;


    //构造函数
    public GifSurfaceView(Context context) {
        super(context);
        initParam();
    }

    public GifSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParam();
    }

    public GifSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParam();
    }


    //线程
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //获取画布(加锁)
            Canvas canvas = holder.lockCanvas();
            canvas.save();
            canvas.scale(zoom,zoom);    //x为水平方向的放大倍数，y为竖直方向的放大倍数。
            //绘制此gif的某一帧，并刷新本身
            movie.draw(canvas,0,0);
            //逐帧绘制图片(图片数量5)
            // 1 2 3 4 5 6 7 8 9 10
            // 1 2 3 4 0 1 2 3 4 0  循环
            movie.setTime((int) (System.currentTimeMillis()%movie.duration()));
            canvas.restore();
            //结束锁定画图，并提交改变,画画完成(解锁)
            holder.unlockCanvasAndPost(canvas);

            handler.postDelayed(runnable , 50);   //50ms表示每50ms绘制一帧
        }
    };



    /**
     * 初始化参数
     */
    private void  initParam(){
        holder = getHolder();
        holder.addCallback(this);
        handler = new Handler();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    /**
     * 计算视图宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //加载GIF图片
        //1.获取GIF图片路径
        if (!TextUtils.isEmpty(path)){
            try {
                InputStream stream = getContext().getAssets().open(path);
                movie = Movie.decodeStream(stream);
                //获取gif的宽高
                int width = movie.width();
                int height = movie.height();
                setMeasuredDimension((int)(width*zoom),(int)(height*zoom));
//                setMeasuredDimension(width,height);
                //执行gif动画
                handler.post(runnable);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        //停止gif动画
        handler.removeCallbacks(runnable);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }
}
/*
 * 整个过程：继承SurfaceView并实现SurfaceHolder.Callback接口 ----> SurfaceView.getHolder()获得SurfaceHolder对象 ---->SurfaceHolder.addCallback(callback)添加回调函数---->SurfaceHolder.lockCanvas()获得Canvas对象并锁定画布----> Canvas绘画 ---->SurfaceHolder.unlockCanvasAndPost(Canvas canvas)结束锁定画图，并提交改变，将图形显示。
*/
