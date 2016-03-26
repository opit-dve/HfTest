package com.example.hellofreshtest.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hellofreshtest.HelloFreshApp;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by srd on 9/22/2015.
 */
public class BaseFragment extends Fragment {

    protected HelloFreshApp mApplication;

    protected CompositeSubscription mCompositeSubscription
            = new CompositeSubscription();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplication = (HelloFreshApp) getActivity().getApplication();
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.unsubscribe();
        super.onDestroy();
    }
}
