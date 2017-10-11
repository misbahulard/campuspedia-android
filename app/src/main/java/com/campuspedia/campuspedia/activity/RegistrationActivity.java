package com.campuspedia.campuspedia.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private BaseApiService mBaseApiService;
    private Context mContext;

    private ProgressDialog mProgressDialog;
    private TextView mTvText1, mTvText2;
    private EditText mEtName, mEtEmail, mEtPassword;
    private Button mBtnRegister, mBtnBack;
    private Typeface tf1, tf2, tf3, tf4, tf5, tf6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mContext = this;
        mBaseApiService = ApiUtils.getApiService();

        mTvText1 = (TextView) findViewById(R.id.text1);
        mTvText2 = (TextView) findViewById(R.id.text2);
        mEtName = (EditText) findViewById(R.id.input_registration_name);
        mEtEmail = (EditText) findViewById(R.id.input_registration_email);
        mEtPassword = (EditText) findViewById(R.id.input_registration_password);
        mBtnRegister = (Button) findViewById(R.id.button_register);
        mBtnBack = (Button) findViewById(R.id.button_back);

        tf1 = Typeface.createFromAsset(getAssets(), "Raleway-ExtraBold.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "SofiaPro.otf");
        tf3 = Typeface.createFromAsset(getAssets(), "SofiaPro.otf");
        tf4 = Typeface.createFromAsset(getAssets(), "SofiaPro.otf");
        tf5 = Typeface.createFromAsset(getAssets(), "SofiaPro.otf");
        tf6 = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");

        mTvText1.setTypeface(tf1);
        mTvText2.setTypeface(tf2);
        mEtName.setTypeface(tf3);
        mEtEmail.setTypeface(tf4);
        mEtPassword.setTypeface(tf5);
        mBtnRegister.setTypeface(tf6);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_registration);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog = ProgressDialog.show(mContext, null, "Register new user..", true, false);
                registrationRequest();
            }
        });
    }

    /**
     * Method ini bergungsi untuk melakukan registrasi user dengan cara mengakses API login
     *
     * @see JSONObject
     * @see BaseApiService
     */
    private void registrationRequest() {
        String name = mEtName.getText().toString();
        String email = mEtEmail.getText().toString();
        String password = mEtPassword.getText().toString();

        if (validateName(mEtName) && validateEmail(mEtEmail) && validatePassword(mEtPassword)) {
            mBaseApiService.registerRequest(name, email, password)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                mProgressDialog.dismiss();

                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().string());
                                    if (jsonObject.getJSONObject("meta").getString("status_code").equals("1")) {
                                        String message = jsonObject.getJSONObject("data").getString("message");
                                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        String message = jsonObject.getJSONObject("data").getString("message");
                                        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                mProgressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "on Failure: ERROR > " + t.toString());
                            mProgressDialog.dismiss();
                            Toast.makeText(mContext, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            mProgressDialog.dismiss();
            Toast.makeText(mContext, "Please fill the form correctly", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method ini digunakan untuk memvalidasi input nama
     *
     * @param et {@link EditText} dari input nama yang bersangkutan
     * @return true jika input nama memenuhi kriteria
     */
    private boolean validateName(EditText et) {
        String text = et.getText().toString();
        if (TextUtils.isEmpty(text)) {
            et.setError(getResources().getString(R.string.error_field_required));
            return false;
        } else {
            et.setError(null);
            return true;
        }
    }

    /**
     * Method ini digunakan untuk memvalidasi input email
     *
     * @param et {@link EditText} dari input email yang bersangkutan
     * @return true jika input email memenuhi kriteria
     */
    private boolean validateEmail(EditText et) {
        String text = et.getText().toString();
        if (TextUtils.isEmpty(text)) {
            et.setError(getResources().getString(R.string.error_field_required));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            et.setError(getResources().getString(R.string.error_invalid_email));
            return false;
        } else {
            et.setError(null);
            return true;
        }
    }

    /**
     * Method ini digunakan untuk memvalidasi input password
     *
     * @param et {@link EditText} dari input password yang bersangkutan
     * @return true jika input password memenuhi kriteria
     */
    private boolean validatePassword(EditText et) {
        String text = et.getText().toString();
        if (TextUtils.isEmpty(text)) {
            et.setError(getResources().getString(R.string.error_field_required));
            return false;
        } else if (et.getText().length() < 6) {
            et.setError(getResources().getString(R.string.error_invalid_password));
            return false;
        } else {
            et.setError(null);
            return true;
        }
    }
}
