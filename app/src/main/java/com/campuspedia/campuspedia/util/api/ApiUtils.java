package com.campuspedia.campuspedia.util.api;

/**
 * Class ini digunakan sebagai alat untuk mengakses interface API Service menggunakan Retrofit
 * @see BaseApiService
 *
 * @author misbahulard
 * @version 20171008
 */
public class ApiUtils {
    // TODO: Change the api url with your own server
    public static final String BASE_URL_API = "http://212.237.8.88:8000/api/";

    /**
     * Method ini berfungsi untuk mendapatkan Api Service
     *
     * @return BaseApiService dari OkHttp
     */
    public static BaseApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
