package com.androidsearch.wikiimage.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.androidsearch.wikiimage.R;
import com.androidsearch.wikiimage.model.Thumbnail;
import com.androidsearch.wikiimage.utils.BitmapTransform;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiImageAdapter extends RecyclerView.Adapter<WikiImageAdapter.MyViewHolder> {

    private ArrayList<Thumbnail> responseResult;

    public WikiImageAdapter(ArrayList<Thumbnail> responseResult) {
        this.responseResult = responseResult;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wikiimage_list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imageUrl = responseResult.get(position).getSource();
        if (null != imageUrl) {
            Picasso.get().load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .transform(new BitmapTransform(200,200))
                    .into(holder.wikiImageView,getCallBack(holder.wikiImageView));
        }
    }

    private Callback getCallBack(final ImageView imageView) {
        return new Callback() {
            @Override
            public void onSuccess() {
                ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .75f, ScaleAnimation.RELATIVE_TO_SELF, .75f);
                scale.setDuration(400);
                imageView.startAnimation(scale);
            }
            @Override
            public void onError(Exception e) {

            }

        };
    }
    @Override
    public int getItemCount() {
        return responseResult.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.wikiImageView)
        ImageView wikiImageView;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
