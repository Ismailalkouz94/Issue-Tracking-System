package com.ismail.its.network;

import com.ismail.its.model.response.IssueResponse;
import com.ismail.its.model.response.LoginResponseMessage;
import com.ismail.its.model.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/login")
    Call<LoginResponseMessage> doLogin(@Query("userName") String userName, @Query("password") String password);

    @POST("/logout")
    Call<LoginResponseMessage> doLogout();

    @GET("/users")
    Call<UserResponse> getUsers();

    @GET("/issues/users/{id}/filter/{filterId}")
    Call<IssueResponse> getIssues(@Path("id") Long userId,@Path("filterId") Long filterId);

}