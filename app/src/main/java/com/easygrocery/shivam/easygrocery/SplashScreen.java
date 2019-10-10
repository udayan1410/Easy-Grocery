package com.easygrocery.shivam.easygrocery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1800);
                    startActivity(new Intent(SplashScreen.this, Login.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
