package com.lfkdsk.bika.response;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("_id")
    private String categoryId;
    private String description;
    private ThumbnailObject thumb;
    private String title;
    private Boolean isWeb;
    private Boolean active;
    private String link;

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setWeb(Boolean web) {
        isWeb = web;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean getWeb() {
        return isWeb;
    }

    public String getLink() {
        return link;
    }

    public Category(String categoryId, String title, String description, ThumbnailObject thumb) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.thumb = thumb;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThumbnailObject getThumb() {
        return this.thumb;
    }

    public void setThumb(ThumbnailObject thumb) {
        this.thumb = thumb;
    }

    public String toString() {
        return "CategoryObject{categoryId='" + this.categoryId + '\'' + ", title='" + this.title + '\'' + ", description='" + this.description + '\'' + ", thumb=" + this.thumb + '}';
    }
}
