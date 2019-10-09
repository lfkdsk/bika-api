package com.lfkdsk.bika.response;


import java.util.List;

public class ComicPage {
    private List<ComicData> docs;
    private int limit;
    private int page;
    private int pages;
    private int total;

    public ComicPage(int total, int limit, int page, int pages, List<ComicData> docs) {
        this.total = total;
        this.limit = limit;
        this.page = page;
        this.pages = pages;
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

    public List<ComicData> getDocs() {
        return this.docs;
    }

    public void setDocs(List<ComicData> docs) {
        this.docs = docs;
    }

    public String toString() {
        return "ComicListData{total=" + this.total + ", limit=" + this.limit + ", page=" + this.page + ", pages=" + this.pages + ", docs=" + this.docs + '}';
    }
}
