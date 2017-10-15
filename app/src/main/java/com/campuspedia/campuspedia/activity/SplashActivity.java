package com.campuspedia.campuspedia.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.util.GlideApp;

public class SplashActivity extends AppCompatActivity {

    ImageView mIvSplashLogo;
    TextView mTvLabel;
    Typeface mTfLabel;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mIvSplashLogo = (ImageView) findViewById(R.id.iv_logo);
        mTvLabel = (TextView)findViewById(R.id.tv_label);
        mTfLabel = Typeface.createFromAsset(getAssets(), "Raleway-ExtraBold.ttf");

        GlideApp.with(this).load(R.drawable.logo_color).into(mIvSplashLogo);

        mTvLabel.setTypeface(mTfLabel);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
