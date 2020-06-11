package com.ismail.its;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ismail.its.model.response.LoginResponseMessage;
import com.ismail.its.network.ApiService;
import com.ismail.its.network.RetrofitClientInstance;
import com.ismail.its.services.AuthenticationService;
import com.ismail.its.utils.Constants;
import com.ismail.its.utils.SharedPrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etUsername.setText("");
        etPassword.setText("");
    }


    public void doLogin(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("User Name is required!");

        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required!");
        } else {
            ProgressDialog progress = new ProgressDialog(this);
            progress.setTitle("Signing In");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
            AuthenticationService.doLogin(getApplicationContext(),progress,username,password);
        }
    }
}
