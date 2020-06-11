package com.ismail.its.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Position implements Serializable {
    private static final long serialVersionUID = -8091879091924046847L;

    @SerializedName("id")
    private Long id;

    @SerializedName("key")
    private String key;

    @SerializedName("descption")
    private String descption;

    public Position(Long id, String key, String descption) {
        this.id = id;
        this.key = key;
        this.descption = descption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescption() {
        return descption;
    }

    public void setDescption(String descption) {
        this.descption = descption;
    }
}