package com.example.hh.callbacktest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User();
        user.setAdpater(new MyAdapter() {
            @Override
            public void doOne() {

            }

            @Override
            public void doTwo() {

            }

            @Override
            public void doThree() {

            }
        });


        MyBtn myBtn = new MyBtn();
        myBtn.addOnClickListener(new MyView.OnClickListener() {
            @Override
            public void onClick() {
                Log.d("", "");
            }
        });

    }


}
