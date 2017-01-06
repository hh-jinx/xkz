package com.example.hh.snackbartest;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tv_bar;
    private CoordinatorLayout coordl;
    Snackbar snackbar;
    private TextInputLayout input_account;
    private TextInputLayout input_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_bar = (TextView) findViewById(R.id.tv_bar);
        input_account = (TextInputLayout) findViewById(R.id.input_account);
        input_pass = (TextInputLayout) findViewById(R.id.input_pass);
        coordl = (CoordinatorLayout) findViewById(R.id.coordl);
        input_account.setEnabled(true);
        input_pass.setEnabled(true);

        snackbar = Snackbar.make(coordl, "actionbar", Snackbar.LENGTH_SHORT).setAction("action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_bar.setText("ttttoast");
            }
        });

        setSnackBarColor(snackbar, Color.GRAY, Color.RED);
        addView2SnackBar(snackbar, R.layout.snack_view, 0);
        addView2SnackBar(snackbar, R.layout.snack_view, 2);

        input_account.setError("not null");

    }

    public void tvShow(View view) {
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                //SnackBar取消触发的情况
                switch (event) {
                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                    case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                    case Snackbar.Callback.DISMISS_EVENT_MANUAL:
                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                }
                super.onDismissed(snackbar, event);
            }

            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
            }
        }).show();

    }

    //设置文本和背景颜色
    private void setSnackBarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();
        view.setBackgroundColor(messageColor);
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(backgroundColor);
    }

    //向SnackBar内添加新样式
    private void addView2SnackBar(Snackbar snackbar, int view, int index) {
        View snackbarView = snackbar.getView();
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarView;

        View addView = LayoutInflater.from(this).inflate(view, null);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
        layoutParams.gravity = Gravity.CENTER;
        snackbarLayout.addView(addView, index, layoutParams);
    }


}
