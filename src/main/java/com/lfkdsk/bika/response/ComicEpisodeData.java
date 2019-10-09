package com.lfkdsk.bika.response;



import java.util.ArrayList;
import java.util.List;

public class ComicEpisodeData {
    List<ComicEpisodeObject> docs;
    int limit;
    int page;
    int pages;
    int total;

    public ComicEpisodeData(List<ComicEpisodeObject> docs, int total, int limit, int page, int pages) {
        this.docs = docs;
        this.total = total;
        this.limit = limit;
        this.page = page;
        this.pages = pages;
    }

    public List<ComicEpisodeObject> getDocs() {
        return this.docs;
    }

    public void setDocs(ArrayList<ComicEpisodeObject> docs) {
        this.docs = docs;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String toString() {
        return "ComicEpisodeData{docs=" + this.docs + ", total=" + this.total + ", limit=" + this.limit + ", page=" + this.page + ", pages=" + this.pages + '}';
    }
}
