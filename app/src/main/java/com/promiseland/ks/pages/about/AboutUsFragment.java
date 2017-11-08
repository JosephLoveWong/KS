package com.promiseland.ks.pages.about;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.promiseland.ks.R;
import com.promiseland.ks.pages.BaseFragment;

import butterknife.OnClick;

public class AboutUsFragment extends BaseFragment {
    public static final String TAG = AboutUsFragment.class.getSimpleName();
    private int mDebugClickStep = 0;
//    @Inject
//    ShareManager mShareManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    public void onResume() {
        super.onResume();
        updateLocaleSpecificUi();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_about_us, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    protected void updateLocaleSpecificUi() {
        getActivity().setTitle(R.string.navigation_about_us);
    }

    @OnClick
    public void onClick(View v) {
//        UrlHelper.openUrlInChromeCustomTab(getActivity(), String.format("http://%s", new Object[]{getUrl(v.getId())}));
//        TrackEvent.event(getEventName(v.getId())).post();
    }

    @OnClick
    public void onClickKSIcon() {
        if (this.mDebugClickStep < 3 || this.mDebugClickStep >= 6) {
            this.mDebugClickStep++;
        }
        if (this.mDebugClickStep == 9) {
            this.mDebugClickStep = 0;
            activateDebugMode();
        }
    }

    @OnClick
    public void onClickAboutUsText() {
        if (this.mDebugClickStep >= 3 && this.mDebugClickStep < 6) {
            this.mDebugClickStep++;
        }
    }

//    private String getUrl(int id) {
//        switch (id) {
//            case R.id.btn_facebook:
//                return getResources().getString(R.string.facebook_name);
//            case R.id.btn_instagram:
//                return "instagram.com/kitchenstories_official";
//            case R.id.btn_pinterest:
//                return "pinterest.com/1kitchenstories";
//            case R.id.btn_twitter:
//                return "twitter.com/1kitchenstories";
//            case R.id.btn_website:
//                return getResources().getString(R.string.website_name);
//            case R.id.btn_youtube:
//                return "youtube.com/1kitchenstories";
//            default:
//                throw new IllegalArgumentException("unknown TextView Id: " + getResources().getResourceEntryName(id));
//        }
//    }
//
//    private String getEventName(int id) {
//        switch (id) {
//            case R.id.btn_facebook:
//                return "BUTTON_FACEBOOK";
//            case R.id.btn_instagram:
//                return "BUTTON_INSTAGRAM";
//            case R.id.btn_pinterest:
//                return "BUTTON_PINTEREST";
//            case R.id.btn_twitter:
//                return "BUTTON_TWITTER";
//            case R.id.btn_website:
//                return "BUTTON_WEB";
//            case R.id.btn_youtube:
//                return "BUTTON_YOUTUBE";
//            default:
//                throw new IllegalArgumentException("unknown TextView Id: " + getResources().getResourceEntryName(id));
//        }
//    }
//
//    public String getPageName() {
//        return "PAGE_ABOUT_US";
//    }
//
//    public TrackEventBuilder addTrackingInformation(TrackEvent event) {
//        return event;
//    }

    public void onAttach(Context context) {
        super.onAttach(context);
//        if (this.mParent instanceof NavDrawerActivity) {
//            ((NavDrawerActivity) this.mParent).setNavigationSelection(9);
//        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
//        if (R.id.action_whats_new == item.getItemId()) {
//            WhatsNewActivity.launch(getContext());
//            return true;
//        } else if (R.id.action_licenses == item.getItemId()) {
//            LicensesActivity.start(getActivity());
//            return true;
//        } else if (R.id.action_tell_friend == item.getItemId()) {
//            this.mShareManager.tellFriend();
//            return true;
//        } else if (item.getItemId() != R.id.action_data_privacy) {
//            return super.onOptionsItemSelected(item);
//        } else {
//            getFragmentManager().beginTransaction().replace(R.id.container, new DataPrivacyFragment(), "DataPrivacyFragment").addToBackStack(TAG).commit();
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private void activateDebugMode() {
//        this.mKitchenPreferences.setDebugModeEnabled(true);
//        SnackbarHelper.show((BaseActivity) getActivity(), (int) R.string.debug_mode_activated_message);
//        getActivity().invalidateOptionsMenu();
    }
}
