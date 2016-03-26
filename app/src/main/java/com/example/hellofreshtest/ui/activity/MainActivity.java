package com.example.hellofreshtest.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hellofreshtest.R;
import com.example.hellofreshtest.ui.fragment.LoginFragment;
import com.example.hellofreshtest.ui.fragment.RecipesFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view) NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // show recipes fragment by default
        if (savedInstanceState == null) {
            mNavigationView.setCheckedItem(R.id.menu_home);
            processMenuClick(R.id.menu_home);
        }
    }

    private void init() {

        ButterKnife.bind(MainActivity.this);

        initToolbar();

        setupDrawerContent(mNavigationView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setCheckedItem(R.id.menu_home);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        processMenuClick(menuItem.getItemId());
                        return true;
                    }
                });
    }

    public void processMenuClick(int menuItemId) {

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (menuItemId) {
            case R.id.menu_login:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, LoginFragment.newInstance())
                        .commit();
                break;
            default:
                // default to recipes
                fragmentManager.beginTransaction()
                        .replace(R.id.container, RecipesFragment.newInstance())
                        .commit();
                break;
        }
    }
}
