package com.campuspedia.campuspedia.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.campuspedia.campuspedia.R;

public class SuggestActivity extends AppCompatActivity {

    TextView mTvText1, mTvText2;
    Button mBtnSuggest;
    Typeface tf1, tf2, tf3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

        mTvText1 = (TextView) findViewById(R.id.text1);
        mTvText2 = (TextView) findViewById(R.id.text2);
        mBtnSuggest = (Button) findViewById(R.id.button_suggest);

        tf1 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        tf3 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");

        mTvText1.setTypeface(tf1);
        mTvText2.setTypeface(tf2);
        mBtnSuggest.setTypeface(tf3);

    }
}
