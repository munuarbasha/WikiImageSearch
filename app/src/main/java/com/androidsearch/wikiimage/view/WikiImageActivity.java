package com.androidsearch.wikiimage.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.androidsearch.wikiimage.R;
import com.androidsearch.wikiimage.adapter.WikiImageAdapter;
import com.androidsearch.wikiimage.model.Thumbnail;
import com.androidsearch.wikiimage.networking.WikiImageWorker;
import com.androidsearch.wikiimage.viewpresenter.WikiImageViewInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiImageActivity extends AppCompatActivity implements WikiImageViewInterface {
    @BindView(R.id.etSearchText)
    EditText etSearchField;
    @BindView(R.id.wikiImageListView)
    RecyclerView wikiImageListView;
    private WikiImageWorker imagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imagePresenter = new WikiImageWorker(this);
        initializeViews();
    }

    private void initializeViews() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        wikiImageListView.setHasFixedSize(true);
        wikiImageListView.setLayoutManager(mLayoutManager);
        wikiImageListView.setItemAnimator(new DefaultItemAnimator());
        etSearchField.addTextChangedListener(new CustomWatcher());
    }

    private void searchWikiImages(String searchKey) {
        Log.i("WikiImageActivity","Search Key "+searchKey);
        imagePresenter.getWikiImages(searchKey);
    }

    @Override
    public void displayImages(ArrayList<Thumbnail> response) {
        if(!response.isEmpty()){
            WikiImageAdapter listAdapter = new WikiImageAdapter(response);
            wikiImageListView.setAdapter(listAdapter);
        }
    }

    @Override
    public void displayError(String errorMessage) {

    }

    public class CustomWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String enteredValue  = s.toString();
            if (enteredValue.length() != 0) {
                searchWikiImages(enteredValue);
            }
        }
    }
}
