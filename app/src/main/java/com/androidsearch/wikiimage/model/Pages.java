package com.androidsearch.wikiimage.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

@JsonAdapter(Pages.PageIdJsonAdapter.class)
public class Pages {

    private ArrayList<PageIds> pageIds;

    public ArrayList<PageIds> getPageIds() {
        return pageIds;
    }

    public void setPageIds(ArrayList<PageIds> pageIds) {
        this.pageIds = pageIds;
    }

    //To Handle multiple Sub Objects inside Pages object from JSON
    public static class PageIdJsonAdapter extends TypeAdapter<Pages> {

        @Override
        public void write(JsonWriter out, Pages value) {
        }

        @Override
        public Pages read(JsonReader reader) throws IOException {
            ArrayList<PageIds> pageIds = new ArrayList<>();
            Gson gson = new Gson();
            reader.beginObject();
            while (reader.hasNext()) {
                reader.nextName();
                pageIds.add((PageIds) gson.fromJson(reader, PageIds.class));
            }
            reader.endObject();
            Pages pages = new Pages();
            PageIds[] array = new PageIds[pageIds.size()];
            pageIds.toArray(array); // fill the array
            pages.setPageIds(pageIds);
            return pages;
        }
    }
}
