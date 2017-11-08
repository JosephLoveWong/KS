package com.promiseland.ks.pages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.promiseland.ks.pages.about.AboutUsFragment;

// TODO
public class FragmentDispatcher {
    private final FragmentManager mFragmentManager;

    public FragmentDispatcher(FragmentManager mManager) {
        this.mFragmentManager = mManager;
    }

    @SuppressLint({"SwitchIntDef"})
    public void displayNavigationDrawerItem(int navSelection, boolean calledByUserClick) {
        Fragment currentSelectionFragment;
        String tag = null;
        switch (navSelection) {
            case 9:
                if (!calledByUserClick && this.mFragmentManager.getBackStackEntryCount() > 0) {
                    currentSelectionFragment = null;
                    break;
                }
                currentSelectionFragment = this.mFragmentManager.findFragmentByTag(AboutUsFragment.TAG);
                if (currentSelectionFragment == null) {
                    currentSelectionFragment = new AboutUsFragment();
                    tag = AboutUsFragment.TAG;
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Navdrawer item not supported");
        }
        if (currentSelectionFragment != null && !currentSelectionFragment.isAdded()) {
            for (int i = 0; i < this.mFragmentManager.getBackStackEntryCount(); i++) {
                this.mFragmentManager.popBackStackImmediate();
            }
            // TODO
//            this.mFragmentManager.beginTransaction().replace(R.id.container, currentSelectionFragment, tag).commit();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mFragmentManager.getBackStackEntryCount() > 0) {
            this.mFragmentManager.findFragmentByTag(this.mFragmentManager.getBackStackEntryAt(this.mFragmentManager.getBackStackEntryCount() - 1).getName()).onActivityResult(requestCode, resultCode, data);
        }
    }
}
