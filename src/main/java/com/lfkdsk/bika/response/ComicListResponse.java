package com.lfkdsk.bika.response;

public class ComicListResponse {
    private ComicPage comics;

    public ComicListResponse(ComicPage comics) {
        this.comics = comics;
    }

    public ComicPage getComics() {
        return this.comics;
    }

    public void setComics(ComicPage comics) {
        this.comics = comics;
    }

    public String toString() {
        return "ComicListResponse{, comics=" + this.comics + '}';
    }
}
