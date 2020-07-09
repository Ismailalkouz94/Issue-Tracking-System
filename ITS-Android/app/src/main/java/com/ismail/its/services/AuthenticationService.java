package com.ismail.its.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.ismail.its.LoginActivity;
import com.ismail.its.NavDrawerActivity;
import com.ismail.its.model.response.LoginResponseMessage;
import com.ismail.its.network.ApiService;
import com.ismail.its.network.RetrofitClientInstance;
import com.ismail.its.utils.Constants;
import com.ismail.its.utils.SharedPrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationService {

    public static void doLogout(Context context) {
        SharedPrefUtils.clearSharedPrefs(context.getSharedPreferences(Constants.SHARED_PERF_NAME, Context.MODE_PRIVATE));
        Intent intent = new Intent(context, LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void doLogin(final Context context, final ProgressDialog progress, String username, String password) {
        {

            /*Create handle for the RetrofitInstance interface*/
            ApiService service = RetrofitClientInstance.getRetrofitInstance(context).create(ApiService.class);
            Call<LoginResponseMessage> call = service.doLogin(username, password);
            call.enqueue(new Callback<LoginResponseMessage>() {
                @Override
                public void onResponse(Call<LoginResponseMessage> call, Response<LoginResponseMessage> response) {
                    if (response.code() == 200) {
                        progress.dismiss();
                        Toast.makeText(context, "Welcome " + response.body().getResponse().getUser().getFirstName(), Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPref = context.getSharedPreferences(Constants.SHARED_PERF_NAME, Context.MODE_PRIVATE);
                        SharedPrefUtils.setSharedPref(sharedPref, Constants.TOKEN, response.body().getResponse().getJwtResponse().getToken());
                        SharedPrefUtils.setSharedPref(sharedPref, Constants.USER_ID, response.body().getResponse().getUser().getId());

                        Intent intent = new Intent(context, NavDrawerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } else {
                        try {
                            progress.dismiss();
                            final JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Toast.makeText(context.getApplicationContext(), jsonObject.get("errMsg").toString(), Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseMessage> call, Throwable t) {
                    progress.dismiss();
                    Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
