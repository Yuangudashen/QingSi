package com.qingsi.qingsi.siyu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingsi.qingsi.R;

/**
 * Created by Administrator on 2016/10/4.
 */

public class RecorderDialog extends Dialog {
    public RecorderDialog(Context context) {
        super(context);
    }

    public RecorderDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private Bitmap image;

        public Builder(Context context) {
            this.context = context;
        }

        public Bitmap getImage() {
            return image;
        }

        public void setImage(Bitmap image) {
            this.image = image;
        }

        public RecorderDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final RecorderDialog dialog = new RecorderDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.chat_dialog_recrorder, null);
            dialog.addContentView(layout, new WindowManager.LayoutParams(
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT
                    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            ImageView img = (ImageView) layout.findViewById(R.id.imageView_dialog);
            img.setImageBitmap(getImage());
            TextView textView = (TextView) layout.findViewById(R.id.textView_dialog);
            textView.setText("向上滑取消.....");
            return dialog;
        }
    }
}
