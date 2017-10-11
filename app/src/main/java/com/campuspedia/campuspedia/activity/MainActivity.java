package com.campuspedia.campuspedia.activity;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bumptech.glide.Glide;
import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.util.GlideApp;
import com.campuspedia.campuspedia.util.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    ImageView mIvProfile;
    TextView mTvName, mTvEmail;
    Button mBtnLogout;
    BottomNavigationBar mBottomNavigationBar;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIvProfile = (ImageView) findViewById(R.id.image_profile);
        mTvName = (TextView) findViewById(R.id.text_name);
        mTvEmail = (TextView) findViewById(R.id.text_email);
        mBtnLogout = (Button) findViewById(R.id.button_log_out);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation);

        sharedPrefManager = new SharedPrefManager(this);

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setActiveColor(R.color.colorPrimary);

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home, getResources().getString(R.string.text_home)))
                .addItem(new BottomNavigationItem(R.drawable.ic_category, getResources().getString(R.string.text_category)))
                .addItem(new BottomNavigationItem(R.drawable.ic_suggest, getResources().getString(R.string.text_suggest)))
                .addItem(new BottomNavigationItem(R.drawable.ic_notification, getResources().getString(R.string.text_notification)))
                .addItem(new BottomNavigationItem(R.drawable.ic_profile, getResources().getString(R.string.text_profile)))
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

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
