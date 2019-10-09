package com.lfkdsk.bika.response;


public class ComicDetailResponse {
    ComicDetail comic;

    public ComicDetailResponse(ComicDetail comic) {
        this.comic = comic;
    }

    public ComicDetail getComic() {
        return this.comic;
    }

    public void setComic(ComicDetail comic) {
        this.comic = comic;
    }

    public String toString() {
        return "ComicDetailResponse{comic=" + this.comic + '}';
    }
}
