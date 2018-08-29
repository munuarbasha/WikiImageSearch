package com.androidsearch.wikiimage.networking;

import android.support.annotation.NonNull;
import android.util.Log;

import com.androidsearch.wikiimage.model.PageIds;
import com.androidsearch.wikiimage.model.Thumbnail;
import com.androidsearch.wikiimage.model.WikiImages;
import com.androidsearch.wikiimage.networking.ApiClient;
import com.androidsearch.wikiimage.networking.ApiInterface;
import com.androidsearch.wikiimage.viewpresenter.WikiImageViewInterface;
import com.androidsearch.wikiimage.viewpresenter.WikiPresenterInterface;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WikiImageWorker implements WikiPresenterInterface {

    private WikiImageViewInterface wikiImageViewInterface;

    public WikiImageWorker(WikiImageViewInterface wikiImageViewInterface) {
        this.wikiImageViewInterface = wikiImageViewInterface;
    }

    @Override
    public void getWikiImages(String key) {
        getWikiImageObservable(key).subscribeWith(getObserver());
    }

    private Observable<WikiImages> getWikiImageObservable(String searchKey) {
        return ApiClient.getClient().create(ApiInterface.class).getPages(searchKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<WikiImages> getObserver() {
        return new DisposableObserver<WikiImages>() {
            @Override
            public void onNext(WikiImages response) {
                if (response != null) {
                    wikiImageViewInterface.displayImages(getThumbnails(response));
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error %s", e.getMessage());
                wikiImageViewInterface.displayError(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("WikiImageWorker", "onComplete Called");
            }
        };
    }

    @NonNull
    private ArrayList<Thumbnail> getThumbnails(WikiImages response) {
        ArrayList<Thumbnail> thumbnails = new ArrayList<>();
        for (PageIds pageData : response.getQuery().getPages().getPageIds()) {
            if (pageData.getThumbnail() != null) {
                thumbnails.add(pageData.getThumbnail());
            }
        }
        return thumbnails;
    }
}
