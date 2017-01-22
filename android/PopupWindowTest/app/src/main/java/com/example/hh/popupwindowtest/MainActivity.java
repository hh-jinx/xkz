package com.example.hh.popupwindowtest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.zip.Inflater;

public class MainActivity extends Activity {

    private Button btn_show_pop;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_show_pop = (Button) findViewById(R.id.btn_show_pop);
        btn_show_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showWindowManager();
                showDialog();
//                showPopupWindow();

            }
        });
    }

    private PopupWindow popupWindow;

    private void showPopupWindow() {

        popupWindow = new PopupWindow(this);
        view = View.inflate(this, R.layout.dialog_show, null);
        view.findViewById(R.id.iv_pop_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                layoutParams.alpha = 1.0f;
                getWindow().setAttributes(layoutParams);
            }
        });
        popupWindow.setContentView(view);

        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);

        layoutParams = getWindow().getAttributes();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        layoutParams.alpha = 0.3f;
        getWindow().setAttributes(layoutParams);

        popupWindow.setBackgroundDrawable(new ColorDrawable(0xFFFFFF));

        popupWindow.showAtLocation(findViewById(R.id.activity_main), Gravity.CENTER, 0, 0);
    }

    private void showDialog() {

        final Dialog dialog = new Dialog(this, R.style.Dialog_Fullscreen);
        view = View.inflate(this, R.layout.dialog, null);
        dialog.setContentView(view);
        view.findViewById(R.id.iv_pop_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.getWindow().setDimAmount(0.7f);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        dialog.getWindow().addFlags(0x04000000);
        dialog.show();

    }

    private void showWindowManager() {
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.format = PixelFormat.TRANSPARENT;

        view = LayoutInflater.from(this).inflate(R.layout.pop_show, null);

        final RelativeLayout rl_pop_show = (RelativeLayout) view.findViewById(R.id.rl_pop_show);
        rl_pop_show.getBackground().setAlpha(175);
        ImageView ivClose = (ImageView) view.findViewById(R.id.iv_pop_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        windowManager.addView(view, layoutParams);
    }

    private void hide() {
        windowManager.removeView(view);
    }

}
