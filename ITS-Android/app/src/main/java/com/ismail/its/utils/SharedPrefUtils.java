package com.ismail.its.utils;

import android.content.SharedPreferences;

import java.util.Locale;

public class SharedPrefUtils {

    public static void setSharedPref(SharedPreferences sharedPref, String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setSharedPref(SharedPreferences sharedPref, String key, int value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void setSharedPref(SharedPreferences sharedPref, String key, Long value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void setSharedPref(SharedPreferences sharedPref, String key, boolean value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getSharedPrefString(SharedPreferences sharedPref, String key) {
        return sharedPref.getString(key, "");
    }

    public static int getSharedPrefeInt(SharedPreferences sharedPref, String key) {
        return sharedPref.getInt(key, -1);
    }

    public static Long getSharedPrefeLong(SharedPreferences sharedPref, String key) {
        return sharedPref.getLong(key, -1L);
    }

    public static Boolean getSharedPrefeBool(SharedPreferences sharedPref, String key) {
        return sharedPref.getBoolean(key, false);
    }

    public static void clearSharedPrefs(SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    public static void removeSharedPref(SharedPreferences sharedPref, String key) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.commit();
    }


}
