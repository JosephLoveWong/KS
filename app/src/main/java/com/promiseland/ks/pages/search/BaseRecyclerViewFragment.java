package com.promiseland.ks.pages.search;

import android.support.v4.widget.NestedScrollView;

import com.promiseland.ks.R;
import com.promiseland.ks.pages.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/1.
 */

public abstract class BaseRecyclerViewFragment extends BaseFragment {
    @BindView(R.id.scroll_view)
    protected NestedScrollView mNestedScrollView;
}
