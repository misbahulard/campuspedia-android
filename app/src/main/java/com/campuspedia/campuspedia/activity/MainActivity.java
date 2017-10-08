package com.campuspedia.campuspedia.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.util.GlideApp;
import com.campuspedia.campuspedia.util.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    ImageView mIvProfile;
    TextView mTvName, mTvEmail;
    Button mBtnLogout;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIvProfile = (ImageView) findViewById(R.id.image_profile);
        mTvName = (TextView) findViewById(R.id.text_name);
        mTvEmail = (TextView) findViewById(R.id.text_email);
        mBtnLogout = (Button) findViewById(R.id.button_log_out);

        sharedPrefManager = new SharedPrefManager(this);

        /**
         * TODO: hapus ini jika fitur menu utama sudah ada
         * Check user is logged in or not
         * when is not logged in, redirect to login activity
         */
        if (!sharedPrefManager.getPrefLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        /**
         * Ketika user sudah login
         */

        String img = sharedPrefManager.getPrefPhotoPath() + sharedPrefManager.getPrefPhoto();

        GlideApp.with(this)
                .load(img)
                .into(mIvProfile);
        mTvName.setText(sharedPrefManager.getPrefName());
        mTvEmail.setText(sharedPrefManager.getPrefEmail());

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.clearPref();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
