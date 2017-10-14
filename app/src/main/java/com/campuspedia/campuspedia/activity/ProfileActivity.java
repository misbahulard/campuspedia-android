package com.campuspedia.campuspedia.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.campuspedia.campuspedia.R;

public class ProfileActivity extends AppCompatActivity {

    TextView mTvText1, mTvText2, mTvText3;
    Button mBtnSignout;
    Typeface tf1, tf2, tf3, tf4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mTvText1 = (TextView) findViewById(R.id.text1);
        mTvText2 = (TextView) findViewById(R.id.text2);
        mTvText3 = (TextView) findViewById(R.id.text3);
        mBtnSignout = (Button) findViewById(R.id.button_sign_out);

        tf1 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "SofiaPro.otf");
        tf3 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        tf4 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");

        mTvText1.setTypeface(tf1);
        mTvText2.setTypeface(tf2);
        mTvText3.setTypeface(tf3);
        mBtnSignout.setTypeface(tf4);
    }
}
