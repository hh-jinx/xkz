package com.example.hh.singlebtntest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn_1);
        btn.setOnClickListener(btnL1);
        btn = (Button) findViewById(R.id.btn_2);
        btn.setOnClickListener(btnL2);

    }


    private View.OnClickListener btnL1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("MainActivity", "bint1");
        }
    };


    private View.OnClickListener btnL2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("MainActivity", "bint2");
        }
    };

}
