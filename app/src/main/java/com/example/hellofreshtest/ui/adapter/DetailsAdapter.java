package com.example.hellofreshtest.ui.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hellofreshtest.R;
import com.example.hellofreshtest.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by srd on 3/25/2016.
 */
public class DetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_STRING = 0;
    public static final int TYPE_IMAGE = 1;

    private Recipe mRecipe;
    private ArrayList<String> mItems = new ArrayList<>();

    public DetailsAdapter(Recipe recipe, ArrayList<String> items) {

        mRecipe = recipe;
        mItems = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_IMAGE;
        }

        return TYPE_STRING;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_STRING) {
            return new StringViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_string, parent, false));
        }

        if (viewType == TYPE_IMAGE) {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_image, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            Picasso.with(imageViewHolder.imageView.getContext())
                    .load(mRecipe.image).into(imageViewHolder.imageView);
        }
        else {
            StringViewHolder stringViewHolder = (StringViewHolder) holder;
            stringViewHolder.textView.setText(mItems.get(position-1));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size() + 1;
    }

    static class StringViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView textView;

        public StringViewHolder(View itemView) {
            super(itemView);

            textView = (AppCompatTextView) itemView;
        }
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView;
        }
    }
}
