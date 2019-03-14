package com.example.storeapplication.View.GreetingScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.storeapplication.R;
import com.example.storeapplication.View.Home.HomeActivity;

public class GreetingScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greetingscreen_layout);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch (Exception e){

                }finally {
                    Intent iHome = new Intent(GreetingScreenActivity.this, HomeActivity.class);
                    startActivity(iHome);
                }
            }
        });

        thread.start();
    }
}
