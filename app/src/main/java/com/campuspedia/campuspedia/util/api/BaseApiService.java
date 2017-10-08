package com.campuspedia.campuspedia.util.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Interface yang berfungsi untuk menyimpan tiap end point url dari API
 *
 * @author misbahulard
 * @version 20171008
 */
public interface BaseApiService {

    /**
     * Method ini berfungsi untuk mengakses API Login menggunakan method
     * Method: POST
     *
     * @param email email user
     * @param password password user
     * @return ResponseBody dari API
     */
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    /**
     * Method ini berfungsi untuk mengakses API Registrasi
     * Method: POST
     *
     * @param name nama user yang akan didaftarkan
     * @param email email user yang akan didaftarkan
     * @param password password user
     * @return ResponseBody dari API
     */
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("password") String password);
}
