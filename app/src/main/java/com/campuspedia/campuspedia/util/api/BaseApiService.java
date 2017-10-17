package com.campuspedia.campuspedia.util.api;

import com.campuspedia.campuspedia.response.CampusListResponse;
import com.campuspedia.campuspedia.response.CampusResponse;
import com.campuspedia.campuspedia.response.EventCategoryListResponse;
import com.campuspedia.campuspedia.model.EventListResponse;
import com.campuspedia.campuspedia.response.EventResponse;
import com.campuspedia.campuspedia.response.MainCategoryListResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("event")
    Call<EventListResponse> eventRequest();

    @GET("event/{id}")
    Call<EventResponse> eventByIdRequest(@Path("id") int eventid);

    @GET("event/category/{id}")
    Call<EventListResponse> eventByCategoryRequest(@Path("id") int categoryid);

    @GET("main-category")
    Call<MainCategoryListResponse> mainCategoryRequest();

    @GET("category/{id}")
    Call<EventCategoryListResponse> categoryRequest(@Path("id") int mainCategoryId);

    @GET("campus")
    Call<CampusListResponse> campusRequest();

    @GET("campus/{id}")
    Call<CampusResponse> campusById(@Path("id") int campusId);
}
