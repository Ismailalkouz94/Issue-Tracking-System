package com.ismail.its.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Type implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("neme")
    private String neme;

    public Type(Long id, String neme) {
        this.id = id;
        this.neme = neme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNeme() {
        return neme;
    }

    public void setNeme(String neme) {
        this.neme = neme;
    }
}
