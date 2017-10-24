package com.promiseland.ks.pages.search;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.arlib.floatingsearchview.FloatingSearchView;

/**
 * Created by ari on 8/16/16.
 */
public abstract class BaseSearchFragment extends Fragment {


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

    protected void attachSearchViewActivityDrawer(FloatingSearchView searchView){
        if(mCallbacks != null){
            mCallbacks.onAttachSearchViewToDrawer(searchView);
        }
    }

    public abstract boolean onActivityBackPress();
}
