package com.example.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        TextView email = findViewById(R.id.emailTextView);
        email.setText(auth.getCurrentUser().getEmail());

        mAdView = findViewById(R.id.adView);
        Button showAdBtn = findViewById(R.id.showInterAd);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interestialAdUnit));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        showAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    showToast();
                }
            }
        });
    }

    private void showToast() {
        Toast.makeText(this, "The interstitial wasn't loaded yet.", Toast.LENGTH_LONG).show();
    }

    public void logout (View view){
        auth.signOut();//logout
        startActivity(new Intent(MainActivity.this,Login.class));
        finish();
    }
}
