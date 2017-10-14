package com.campuspedia.campuspedia.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.campuspedia.campuspedia.R;

public class KampusActivity extends AppCompatActivity {

    TextView mTvText1, mTvText2, mTvText3, mTvText4, mTvText5, mTvText6, mTvText7, mTvText8;
    Typeface tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kampus);

        mTvText1 = (TextView) findViewById(R.id.text1);
        mTvText2 = (TextView) findViewById(R.id.text2);
        mTvText3 = (TextView) findViewById(R.id.text3);
        mTvText4 = (TextView) findViewById(R.id.text4);
        mTvText5 = (TextView) findViewById(R.id.text5);
        mTvText6 = (TextView) findViewById(R.id.text6);
        mTvText7 = (TextView) findViewById(R.id.text7);
        mTvText8 = (TextView) findViewById(R.id.text8);

        tf1 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        tf3 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        tf4 = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        tf5 = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        tf6 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        tf7 = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        tf8 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");

        mTvText1.setTypeface(tf1);
        mTvText2.setTypeface(tf2);
        mTvText3.setTypeface(tf3);
        mTvText4.setTypeface(tf4);
        mTvText5.setTypeface(tf5);
        mTvText6.setTypeface(tf6);
        mTvText7.setTypeface(tf7);
        mTvText8.setTypeface(tf8);
    }
}
