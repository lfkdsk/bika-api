package com.lfkdsk.bika.response;

import java.util.ArrayList;
import java.util.List;

public class TagListResponse {
    List<String> tags;

    public TagListResponse(ArrayList<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
