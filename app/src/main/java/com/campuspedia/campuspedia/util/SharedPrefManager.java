package com.campuspedia.campuspedia.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class ini digunakan untuk mengatur shared preferences user (session)
 *
 * @author misbahulard
 * @version 20171008
 */

public class SharedPrefManager {
    public static final String PREF_APP = "spCampuspediaApp";
    public static final String PREF_NAME = "spName";
    public static final String PREF_EMAIL = "spEmail";
    public static final String PREF_API_TOKEN = "spApiToken";
    public static final String PREF_PHOTO = "spPhoto";
    public static final String PREF_LOGGED_IN = "spLoggedIn";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    /**
     * Constructor untuk membuat Shared Preferences baru
     *
     * @param context activity shared preferences dibuat
     */
    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }

    /**
     * Method yang berfungsi untuk menyimpan preferences bertipe data string
     *
     * @param key kata kunci shared preferences yang akan disimpan nilainya
     * @param value (String) nilai yang akan disimpan ke dalam kunci shared preferences
     */
    public void savePrefString(String key, String value) {
        spEditor.putString(key, value);
        spEditor.commit();
    }

    /**
     * Method yang berfungsi untuk menyimpan preferences bertipe data integer
     *
     * @param key kata kunci shared preferences yang akan disimpan nilainya
     * @param value (int) nilai yang akan disimpan ke dalam kunci shared preferences
     */
    public void savePrefInt(String key, int value) {
        spEditor.putInt(key, value);
        spEditor.commit();
    }

    /**
     * Method yang berfungsi untuk menyimpan preferences bertipe data boolean
     *
     * @param key kata kunci shared preferences yang akan disimpan nilainya
     * @param value (boolean) nilai yang akan disimpan ke dalam kunci shared preferences
     */
    public void savePrefBoolean(String key, boolean value) {
        spEditor.putBoolean(key, value);
        spEditor.commit();
    }

    /**
     * Method yang berfungsi untuk mengambil nilai shared preferences sesuai kunci
     *
     * @return (String) nilai dari shared preferences PREF_NAME
     */
    public String getPrefName() {
        return sharedPreferences.getString(PREF_NAME, "");
    }

    /**
     * Method yang berfungsi untuk mengambil nilai shared preferences sesuai kunci
     *
     * @return (String) nilai dari shared preferences PREF_EMAIL
     */
    public String getPrefEmail() {
        return sharedPreferences.getString(PREF_EMAIL, "");
    }

    /**
     * Method yang berfungsi untuk mengambil nilai shared preferences sesuai kunci
     *
     * @return (String) nilai dari shared preferences PREF_API_TOKEN
     */
    public String getPrefApiToken() {
        return sharedPreferences.getString(PREF_API_TOKEN, "");
    }

    /**
     * Method yang berfungsi untuk mengambil nilai shared preferences sesuai kunci
     *
     * @return (String) nilai dari shared preferences PREF_PHOTO
     */
    public String getPrefPhoto() {
        return sharedPreferences.getString(PREF_PHOTO, "");
    }

    /**
     * Method yang berfungsi untuk mengambil nilai shared preferences sesuai kunci
     *
     * @return (boolean) nilai dari shared preferences PREF_LOGGED_IN
     */
    public Boolean getPrefLoggedIn() {
        return sharedPreferences.getBoolean(PREF_LOGGED_IN, false);
    }

    /**
     * Method yang berfungsi untuk menghapus semua shared preferences
     */
    public void clearPref() {
        spEditor.clear().commit();
    }
}
