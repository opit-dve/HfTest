package com.example.hellofreshtest.ui.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.hellofreshtest.R;
import com.example.hellofreshtest.model.Recipe;
import com.example.hellofreshtest.ui.activity.DetailsActivity;
import com.example.hellofreshtest.ui.adapter.RecipesAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.app.AppObservable;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Created by srd on 9/22/2015.
 */
public class RecipesFragment extends BaseFragment implements RecipesAdapter.RecipesAdapterCallbacks {

    @Bind(R.id.refresh_layout) SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.empty) AppCompatTextView mTvEmpty;

    private RecipesAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Recipe> mItems = new ArrayList<>();

    public static RecipesFragment newInstance() {

        RecipesFragment fragment = new RecipesFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ButterKnife.bind(RecipesFragment.this, view);

        init();

        loadRecipes();
    }

    private void init() {

        mRefreshLayout.setEnabled(false);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecipesAdapter(mItems, RecipesFragment.this);

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {

                if (mItems.size() > 0) {
                    mTvEmpty.setVisibility(View.GONE);
                } else {
                    mTvEmpty.setVisibility(View.VISIBLE);
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }


    /**
     * Load recipes in background
     */
    private void loadRecipes() {

        mCompositeSubscription.add(
                AppObservable.bindFragment(RecipesFragment.this,
                        createRecipesObservable())
                        .subscribe(
                                new Action1<ArrayList<Recipe>>() {
                                    @Override
                                    public void call(ArrayList<Recipe> recipes) {
                                        mItems.clear();
                                        mItems.addAll(recipes);
                                        mAdapter.notifyDataSetChanged();
                                    }
                                },
                                new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Toast.makeText(getActivity(), R.string.error_loading_recipes,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                )
        );
    }

    /**
     * Create Observable object with recipes from raw json file
     * @return Observable
     */
    private Observable<ArrayList<Recipe>> createRecipesObservable() {

        return Observable.create(new Observable.OnSubscribe<ArrayList<Recipe>>() {
            @Override
            public void call(Subscriber<? super ArrayList<Recipe>> subscriber) {

                ArrayList<Recipe> recipes = null;
                try {
                    InputStream inputStream = getActivity().getResources().openRawResource(R.raw.recipes);
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    Gson gson = new Gson();
                    recipes = gson.fromJson(reader, new TypeToken<ArrayList<Recipe>>(){}.getType());

                    subscriber.onNext(recipes);
                    subscriber.onCompleted();

                } catch (Resources.NotFoundException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public void onRecipeItemClick(View view, int position) {

        Recipe recipe = mItems.get(position);

        if (recipe != null) {
            switch (view.getId()) {
                case R.id.favorite:
                    CheckedTextView checkedTextView = (CheckedTextView) view;
                    recipe.favorite = !recipe.favorite;
                    checkedTextView.setChecked(recipe.favorite);
                    break;

                case R.id.image:
                case R.id.title:
                case R.id.description:
                    // go to details

                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    // Pass data object in the bundle and populate details activity.
                    intent.putExtra(DetailsActivity.EXTRA_RECIPE, recipe);
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(getActivity(), view, getString(R.string.tn_image));
                    getActivity().startActivity(intent, options.toBundle());
                    break;

            }
        }
    }

    @Override
    public void onRecipeItemRating(RatingBar ratingBar, float rating, int position) {
        Recipe recipe = mItems.get(position);
        if (recipe != null) {
            recipe.userRating = rating;
        }
    }
}
