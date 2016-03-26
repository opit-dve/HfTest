package com.example.hellofreshtest.ui.adapter;

import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.hellofreshtest.R;
import com.example.hellofreshtest.model.Recipe;
import com.example.hellofreshtest.util.CircleTransform;
import com.example.hellofreshtest.util.ClipOutlineProvider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srd on 9/23/2015.
 */
public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    public interface RecipesAdapterCallbacks {
        void onRecipeItemClick(View view, int position);
        void onRecipeItemRating(RatingBar ratingBar, float rating, int position);
    }

    private ArrayList<Recipe> mItems = new ArrayList<>();
    private RecipesAdapterCallbacks mDelegate;
    private final ClipOutlineProvider mOutline;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image) ImageView image;
        @Bind(R.id.title) AppCompatTextView title;
        @Bind(R.id.description) AppCompatTextView description;
        @Bind(R.id.favorite) AppCompatCheckedTextView favorite;
        @Bind(R.id.rating_bar) AppCompatRatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(RecipesAdapter.ViewHolder.this, itemView);
        }
    }

    public RecipesAdapter(ArrayList<Recipe> items, RecipesAdapterCallbacks delegate) {

        mItems = items;
        mDelegate = delegate;
        mOutline = new ClipOutlineProvider();
    }

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recipe, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.ViewHolder viewHolder, final int position) {

        Recipe item = mItems.get(position);

        // image
        Picasso.with(viewHolder.image.getContext())
                .load(item.image)
                //.transform(new CircleTransform())
                .into(viewHolder.image);

        viewHolder.image.setOutlineProvider(mOutline);
        viewHolder.image.setClipToOutline(true);

        viewHolder.title.setText(item.name);
        viewHolder.description.setText(item.headline);

        viewHolder.ratingBar.setRating(item.userRating);
        viewHolder.favorite.setChecked(item.favorite);

        viewHolder.title.setOnClickListener(crateClickListener(position));
        viewHolder.image.setOnClickListener(crateClickListener(position));
        viewHolder.description.setOnClickListener(crateClickListener(position));
        viewHolder.favorite.setOnClickListener(crateClickListener(position));

        viewHolder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if (fromUser && mDelegate != null) {
                    mDelegate.onRecipeItemRating(ratingBar, rating, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    private View.OnClickListener crateClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mDelegate != null) {
                    mDelegate.onRecipeItemClick(v, position);
                }
            }
        };
    }
}
