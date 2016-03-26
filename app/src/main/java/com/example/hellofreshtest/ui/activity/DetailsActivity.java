package com.example.hellofreshtest.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.hellofreshtest.R;
import com.example.hellofreshtest.model.Recipe;
import com.example.hellofreshtest.ui.adapter.DetailsAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srd on 3/25/2016.
 */
public class DetailsActivity extends BaseActivity {

    public static final String EXTRA_RECIPE = "recipe";

    @Bind(R.id.image) ImageView mImageView;
    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        // Postpone the shared element enter transition.
        postponeEnterTransition();

        mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE);
        if (mRecipe == null) {
            return;
        }

        ButterKnife.bind(this);
        init();

        scheduleStartPostponedTransition(mRecyclerView);
    }

    private void init() {

        Picasso.with(DetailsActivity.this)
                .load(mRecipe.image).into(mImageView);

        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add("Item "+i);
        }

        DetailsAdapter adapter = new DetailsAdapter(mRecipe, items);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        mRecyclerView.setAdapter(adapter);

    }

    /**
     * Schedules the shared element transition to be started immediately
     * after the shared element has been measured and laid out within the
     * activity's view hierarchy. Some common places where it might make
     * sense to call this method are:
     *
     * (1) Inside a Fragment's onCreateView() method (if the shared element
     *     lives inside a Fragment hosted by the called Activity).
     *
     * (2) Inside a Picasso Callback object (if you need to wait for Picasso to
     *     asynchronously load/scale a bitmap before the transition can begin).
     *
     * (3) Inside a LoaderCallback's onLoadFinished() method (if the shared
     *     element depends on data queried by a Loader).
     */
    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }
}
