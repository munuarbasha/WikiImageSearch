package com.androidsearch.wikiimage.networking;

import com.androidsearch.wikiimage.model.WikiImages;
import com.androidsearch.wikiimage.utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api.php?action=query&format=json&prop=pageimages&generator=prefixsearch&piprop=thumbnail&pithumbsize=200&pilimit=50")
    Observable<WikiImages> getPages(@retrofit2.http.Query(Constants.SEARCH_QUERY) String searchKey);
}
