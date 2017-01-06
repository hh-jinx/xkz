package com.example.hh.callbacktest;

/**
 * Created by hh on 17/1/6.
 */

public class MyView {

    OnClickListener clickListener;

    interface OnClickListener {

        void onClick();

    }

    public void addOnClickListener(OnClickListener listener) {
        this.clickListener = listener;
    }

    public void doClick() {
        if (clickListener != null) {
            clickListener.onClick();
        }
    }

}
