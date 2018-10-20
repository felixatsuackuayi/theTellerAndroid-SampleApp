package com.example.fackuayi.afelautoworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static java.lang.Thread.sleep;

public class splash extends AppCompatActivity {

    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashImage = (ImageView) findViewById(R.id.imagesplash);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.myanimation);
//         splashImage.startAnimation(myanim);

         Thread myThread = new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     sleep(10000);
                     Intent i = new Intent(splash.this,MainActivity.class);
                     startActivity(i);


                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

             }
         });
         myThread.start();
    }
}
