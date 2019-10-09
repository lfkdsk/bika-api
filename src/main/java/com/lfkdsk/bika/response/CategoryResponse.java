
package com.lfkdsk.bika.response;


import java.util.List;

public class CategoryResponse {
    public List<CategoryObject> categories;

    public CategoryResponse(List<CategoryObject> categories) {
        this.categories = categories;
    }

    public List<CategoryObject> getCategories() {
        return this.categories;
    }

    public void setCategories(List<CategoryObject> categories) {
        this.categories = categories;
    }

    public String toString() {
        return "CategoryResponse{categories=" + this.categories + '}';
    }
}
