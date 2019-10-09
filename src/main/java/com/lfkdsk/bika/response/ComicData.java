package com.lfkdsk.bika.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComicData {
    public String author;
    public List<String> categories;
    @SerializedName("_id")
    public String comicId;
    @SerializedName("epsCount")
    public int episodeCount;
    public boolean finished;
    public int likesCount;
    public int pagesCount;
    public ThumbnailObject thumb;
    public String title;
}
