package com.campuspedia.campuspedia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.fragment.HomeFragment;
import com.campuspedia.campuspedia.util.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    BottomNavigationBar mBottomNavigationBar;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = new SharedPrefManager(this);

        /**
         * Menambahkan bottom navigation bar
         */
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation);
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
         * Inisialisasi fragment awal {@link HomeFragment}
         */
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root_layout, HomeFragment.newInstance(), "home")
                    .commit();
        }

        /**
         * TODO: hapus ini jika fitur navigasi selesai
         */
        if (!sharedPrefManager.getPrefLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
