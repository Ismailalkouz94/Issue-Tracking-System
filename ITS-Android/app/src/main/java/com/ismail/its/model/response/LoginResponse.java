package com.ismail.its.model.response;

import com.google.gson.annotations.SerializedName;
import com.ismail.its.model.User;

public class LoginResponse  {
    @SerializedName("user")
    private User user;
    @SerializedName("jwtResponse")
    private JwtResponse jwtResponse;

    public LoginResponse(User user, JwtResponse jwtResponse) {
        this.user = user;
        this.jwtResponse = jwtResponse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JwtResponse getJwtResponse() {
        return jwtResponse;
    }

    public void setJwtResponse(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }
}