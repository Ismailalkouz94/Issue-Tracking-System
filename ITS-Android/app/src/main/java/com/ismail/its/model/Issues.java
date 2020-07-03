package com.ismail.its.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Issues implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("attachment")
    private String attachment;

    @SerializedName("user")
    private User user;

    @SerializedName("assignTo")
    private User assignTo;

    @SerializedName("status")
    private Status status;

    @SerializedName("type")
    private Type type;

    @SerializedName("createDateTime")
    private String createDateTime;

    @SerializedName("updateDateTime")
    private String updateDateTime;

    public Issues(Long id, String title, String description, String attachment, User user, User assignTo, Status status, Type type, String createDateTime, String updateDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.attachment = attachment;
        this.user = user;
        this.assignTo = assignTo;
        this.status = status;
        this.type = type;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(User assignTo) {
        this.assignTo = assignTo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
