package com.ismail.its.network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.ismail.its.LoginActivity;
import com.ismail.its.services.AuthenticationService;
import com.ismail.its.utils.Constants;
import com.ismail.its.utils.SharedPrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://10.0.2.2:8080";
//    private static final String BASE_URL = "http://172.20.10.6:8080";

    public static Retrofit getRetrofitInstance(final Context context) {
//        final String token = SharedPrefUtils.getSharedPrefString(context.getSharedPreferences(Constants.SHARED_PERF_NAME, Context.MODE_PRIVATE), Constants.TOKEN);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final String token = SharedPrefUtils.getSharedPrefString(context.getSharedPreferences(Constants.SHARED_PERF_NAME, Context.MODE_PRIVATE), Constants.TOKEN);

                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                Response response = chain.proceed(newRequest);
                if (response.code()==401){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        final JSONObject finalJsonObject = jsonObject;
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Toast.makeText(context.getApplicationContext(), finalJsonObject.get("errMsg").toString(), Toast.LENGTH_SHORT).show();
                                    AuthenticationService.doLogout(context);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return chain.proceed(newRequest);
            }
        })
                .addInterceptor(interceptor)
                .build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)

                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}