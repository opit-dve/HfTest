package com.example.hellofreshtest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hellofreshtest.R;
import com.example.hellofreshtest.util.Validation;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by srd on 9/22/2015.
 */
public class LoginFragment extends BaseFragment {

    @Bind(R.id.email) AppCompatEditText mEmail;
    @Bind(R.id.password) AppCompatEditText mPassword;

    public static LoginFragment newInstance() {

        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ButterKnife.bind(LoginFragment.this, view);

        mPassword.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    submit();

                    return true;
                }

                return false;
            }
        });
    }

    @OnClick(R.id.submit)
    public void submit() {
        if (validateFields()) {
            // TODO submit data to server...
        }
    }


    private boolean validateFields() {

        if (!Validation.isValidEmail(mEmail.getText().toString())) {
            mEmail.setError(getString(R.string.error_invalid_email_address));
            return false;
        }
        else if (TextUtils.isEmpty(mPassword.getText().toString().trim())) {
            mPassword.setError(getString(R.string.error_empty_password));
            return false;
        }

        return true;
    }
}
