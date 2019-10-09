package com.lfkdsk.bika.response;

import com.google.gson.annotations.SerializedName;

public class ComicEpisodeObject {
    @SerializedName("_id")
    String episodeId;
    int order;
    boolean selected;
    int status;
    String title;
    @SerializedName("updated_at")
    String updatedAt;

    public ComicEpisodeObject(String episodeId, String title, int order, String updatedAt) {
        this.episodeId = episodeId;
        this.title = title;
        this.order = order;
        this.updatedAt = updatedAt;
        this.status = 0;
        this.selected = false;
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String toString() {
        return "ComicEpisodeObject{episodeId='" + this.episodeId + '\'' + ", title='" + this.title + '\'' + ", order=" + this.order + ", updatedAt='" + this.updatedAt + '\'' + ", status=" + this.status + ", selected=" + this.selected + '}';
    }

    public int describeContents() {
        return 0;
    }
}
