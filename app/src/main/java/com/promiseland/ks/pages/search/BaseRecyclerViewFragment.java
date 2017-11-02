package com.promiseland.ks.pages.search;

import com.promiseland.ks.R;
import com.promiseland.ks.pages.BaseFragment;
import com.promiseland.ks.view.base.EmptyStateRecyclerView;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/1.
 */

public abstract class BaseRecyclerViewFragment extends BaseFragment {
    @BindView(R.id.empty_state_recycler_view)
    protected EmptyStateRecyclerView mEmptyStateRecyclerView;
}
