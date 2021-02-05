package com.creative.cosmonaut;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        Thread animation_200_kgkvmbof = new Thread() {
                    @Override
            public void run (){
                        ImageView animation_200_kgkvmbof = findViewById(R.id.gifImageView);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_logo);
                        animation_200_kgkvmbof.startAnimation(animation);


                    }

        };
        animation_200_kgkvmbof.start();


        Thread titleAnimation = new Thread() {
            @Override
            public void run (){
               TextView titleanimation = findViewById(R.id.appname);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_author);
                titleanimation.startAnimation(animation);


            }

        };
        titleAnimation.start();
        Thread authorAnimation = new Thread() {
            @Override
            public void run (){
                TextView author = findViewById(R.id.copyright);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_author);
                author.startAnimation (animation);



            }

        };
authorAnimation.start();


        Thread redirect = new Thread() {
            @Override
            public void run (){

             try {
                 sleep (4500);
                 Intent i = new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(i);
                 finish();
super.run();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }


            }

        };
        redirect.start();


    }
}