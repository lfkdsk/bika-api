package com.lfkdsk.bika.response;


public class ComicPagesResponse {
    ComicEpisode ep;
    ComicPageData pages;

    public ComicPagesResponse(ComicPageData pages, ComicEpisode ep) {
        this.pages = pages;
        this.ep = ep;
    }

    public ComicPageData getPages() {
        return this.pages;
    }

    public void setPages(ComicPageData pages) {
        this.pages = pages;
    }

    public ComicEpisode getEp() {
        return this.ep;
    }

    public void setEp(ComicEpisode ep) {
        this.ep = ep;
    }

    public String toString() {
        return "ComicPagesResponse{pages=" + this.pages + ", ep=" + this.ep + '}';
    }
}
