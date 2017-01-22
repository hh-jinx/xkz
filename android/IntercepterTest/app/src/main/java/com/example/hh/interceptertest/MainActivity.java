package com.example.hh.interceptertest;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerIntercepter())
                .build();

        final Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    class LoggerIntercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            long t1 = System.nanoTime();
            Logger logger = Logger.getGlobal();
            logger.info(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            logger.info(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }


}
