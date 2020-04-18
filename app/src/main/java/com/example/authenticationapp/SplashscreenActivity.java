package com.example.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;

public class SplashscreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this);
        setContentView(R.layout.activity_splashscreen);

        Animation heartBeatAnim =
                AnimationUtils.loadAnimation(this, R.anim.bounce);
        TextView text = findViewById(R.id.textView4);

        text.setAnimation(heartBeatAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(FirebaseAuth.getInstance().getCurrentUser() == null){
                    Intent RegIntent = new Intent(SplashscreenActivity.this,Login.class);
                    startActivity(RegIntent);
                    finish();
                }else{
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
    }
}
