
package com.androidsearch.wikiimage.model;

import com.google.gson.annotations.SerializedName;

public class WikiImages {

    @SerializedName("query")
    private Query query;
    public Query getQuery() {
        return query;
    }
    public void setQuery(Query query) {
        this.query = query;
    }

}
