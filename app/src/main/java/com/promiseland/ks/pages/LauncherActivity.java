package com.promiseland.ks.pages;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.promiseland.ks.R;
import com.promiseland.ks.base.utils.ActivityUtil;
import com.promiseland.ks.pages.about.AboutUsFragment;
import com.promiseland.ks.pages.search.BaseSearchBarRecyclerViewFragment;
import com.promiseland.ks.pages.search.ScrollingSearchBarRecyclerViewFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LauncherActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSearchBarRecyclerViewFragment.SearchFragmentCallbacks {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.fragment_container)
    FrameLayout mFrameLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);

        mNavigationView.setNavigationItemSelectedListener(this);

        ActivityUtil.replaceFragment(getSupportFragmentManager(), new ScrollingSearchBarRecyclerViewFragment(), R.id.fragment_container);
    }

    @Override
    public void onBackPressed() {
        @SuppressLint("RestrictedApi") List fragments = getSupportFragmentManager().getFragments();
        BaseSearchBarRecyclerViewFragment currentFragment = (BaseSearchBarRecyclerViewFragment) fragments.get(fragments.size() - 1);

        if (!currentFragment.onActivityBackPress()) {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {
            ActivityUtil.replaceFragment(getSupportFragmentManager(), new AboutUsFragment(), R.id.fragment_container);
        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
        searchView.attachNavigationDrawerToMenuButton(mDrawer);
    }


}
