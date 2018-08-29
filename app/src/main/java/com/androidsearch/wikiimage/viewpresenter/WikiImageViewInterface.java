package com.androidsearch.wikiimage.viewpresenter;

import com.androidsearch.wikiimage.model.Thumbnail;

import java.util.ArrayList;

public interface WikiImageViewInterface {

    void displayImages(ArrayList<Thumbnail> response);
    void displayError(String errorMessage);
}
