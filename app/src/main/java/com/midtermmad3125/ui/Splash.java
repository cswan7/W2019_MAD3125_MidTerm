package com.midtermmad3125.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.midtermmad3125.R;

public class Splash extends MainCityActivity {

    Handler handler;
     //https://abhiandroid.com/programming/splashscreen
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this,MainCityActivity.class);
                startActivity(intent);
                finish();
            }
        }),3000;

    }
}
