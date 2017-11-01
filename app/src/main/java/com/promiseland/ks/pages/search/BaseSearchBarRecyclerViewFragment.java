package com.promiseland.ks.pages.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.promiseland.ks.R;
import com.promiseland.ks.base.utils.APILevelHelper;
import com.promiseland.ks.base.utils.ConfigurationUtils;
import com.promiseland.ks.base.utils.LogUtil;

import butterknife.BindView;

/**
 * Created by ari on 8/16/16.
 */
public abstract class BaseSearchBarRecyclerViewFragment extends BaseRecyclerViewFragment {

    @BindView(R.id.floating_search_view)
    protected FloatingSearchView mSearchView;
    @BindView(R.id.appbar_layout)
    protected AppBarLayout mAppBar;

    private SearchFragmentCallbacks mCallbacks;

    public interface SearchFragmentCallbacks{

        void onAttachSearchViewToDrawer(FloatingSearchView searchView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchFragmentCallbacks) {
            mCallbacks = (SearchFragmentCallbacks) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BaseExampleFragmentCallbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moveAppBarBelowStatusBar();

        // TODO 移动内容
//        moveRecyclerViewBelowAppBar();
    }

    protected void attachSearchViewActivityDrawer(FloatingSearchView searchView){
        if(mCallbacks != null){
            mCallbacks.onAttachSearchViewToDrawer(searchView);
        }
    }

    public abstract boolean onActivityBackPress();

    private void moveAppBarBelowStatusBar() {
        if (APILevelHelper.isAPILevelMinimal(21)) {
            int statusBarHeight = ConfigurationUtils.getStatusBarHeight(getContext());
            translateView(this.mSearchView, -statusBarHeight);
            this.mAppBar.setTranslationY((float) (-statusBarHeight));
            ViewGroup.LayoutParams layoutParams = this.mAppBar.getLayoutParams();

            LogUtil.e(LogUtil.TAG, "ori height " + layoutParams.height);
            layoutParams.height += statusBarHeight;
            LogUtil.e(LogUtil.TAG, "new  height" + layoutParams.height);
        }
    }

    private void moveRecyclerViewBelowAppBar() {
        this.mNestedScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                BaseSearchBarRecyclerViewFragment.this.mNestedScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int appBarHeight = BaseSearchBarRecyclerViewFragment.this.mAppBar.getLayoutParams().height;
                BaseSearchBarRecyclerViewFragment.this.mNestedScrollView.setTranslationY((float) (-appBarHeight));
                Context applicationContext = BaseSearchBarRecyclerViewFragment.this.getContext();
                BaseSearchBarRecyclerViewFragment.this.mNestedScrollView.getLayoutParams().height = (ConfigurationUtils.getScreenHeight(BaseSearchBarRecyclerViewFragment.this.getContext()) + appBarHeight) - ConfigurationUtils.getStatusBarHeight(applicationContext);
                BaseSearchBarRecyclerViewFragment.this.mNestedScrollView.requestLayout();
            }
        });
    }

}
