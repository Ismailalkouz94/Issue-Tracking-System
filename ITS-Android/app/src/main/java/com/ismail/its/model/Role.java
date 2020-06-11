package com.ismail.its.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Role implements Serializable {
    private static final long serialVersionUID = -8091879091924046846L;

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}