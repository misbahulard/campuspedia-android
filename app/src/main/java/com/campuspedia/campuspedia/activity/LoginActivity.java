package com.campuspedia.campuspedia.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.util.SharedPrefManager;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog mLoginProgressDialog;
    private BaseApiService mBaseApiService;
    private SharedPrefManager sharedPrefManager;
    private Context mContext;
    private TextView mtvRegister;
    private EditText mEtEmail, mEtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mtvRegister = (TextView) findViewById(R.id.text_register);
        mEtEmail = (EditText) findViewById(R.id.input_login_email);
        mEtPassword = (EditText) findViewById(R.id.input_login_password);
        mBtnLogin = (Button) findViewById(R.id.button_sign_in);

        mContext = this;
        mBaseApiService = ApiUtils.getApiService();
        sharedPrefManager = new SharedPrefManager(mContext);

        /**
         * Listener untuk {@link mtvRegister}
         * Berfungsi untuk berpindah intent ke activity {@link RegistrationActivity}
         */
        mtvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Listener untuk {@link mBtnLogin}
         * Berfungsi untuk melakukan login aplikasi dengan cara mengakses API login terlebih dahulu
         * Dengan cara memanggil method loginReguest
         */
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginProgressDialog = ProgressDialog.show(mContext, null, "Try to login..", true, false);
                loginRequest();
            }
        });
    }

    /**
     * Method ini bergungsi untuk melakukan login user dengan cara mengakses API login
     * Dan menyimpan preferences user ke dalam {@link SharedPrefManager}
     *
     * @see JSONObject
     * @see BaseApiService
     * @see SharedPrefManager
     */
    private void loginRequest() {
        String email = mEtEmail.getText().toString();
        String password = mEtPassword.getText().toString();

        if (validateEmail(mEtEmail) && validatePassword(mEtPassword)) {
            mBaseApiService.loginRequest(email, password)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                mLoginProgressDialog.dismiss();

                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().string());
                                    if (jsonObject.getJSONObject("meta").getString("status_code").equals("1")) {
                                        Toast.makeText(mContext, "Login success", Toast.LENGTH_SHORT).show();
                                        String name = jsonObject.getJSONObject("data").getString("name");
                                        String email = jsonObject.getJSONObject("data").getString("email");
                                        String api_token = jsonObject.getJSONObject("data").getString("api_token");
                                        String photo = jsonObject.getJSONObject("data").getString("photo");
                                        String photo_path = jsonObject.getJSONObject("meta").getString("user_img_path");

                                        sharedPrefManager.savePrefString(SharedPrefManager.PREF_NAME, name);
                                        sharedPrefManager.savePrefString(SharedPrefManager.PREF_EMAIL, email);
                                        sharedPrefManager.savePrefString(SharedPrefManager.PREF_API_TOKEN, api_token);
                                        sharedPrefManager.savePrefString(SharedPrefManager.PREF_PHOTO, photo);
                                        sharedPrefManager.savePrefString(SharedPrefManager.PREF_PHOTO_PATH, photo_path);
                                        sharedPrefManager.savePrefBoolean(SharedPrefManager.PREF_LOGGED_IN, true);

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        String error_message = jsonObject.getJSONObject("meta").getString("message");
                                        Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                mLoginProgressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "on Failure: ERROR > " + t.toString());
                            mLoginProgressDialog.dismiss();
                            Toast.makeText(mContext, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            mLoginProgressDialog.dismiss();
            Toast.makeText(mContext, "Please fill the form correctly", Toast.LENGTH_LONG).show();
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
        } else {
            et.setError(null);
            return true;
        }
    }
}
