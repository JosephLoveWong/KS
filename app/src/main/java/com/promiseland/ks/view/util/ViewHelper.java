package com.promiseland.ks.view.util;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.promiseland.ks.base.utils.APILevelHelper;
import com.promiseland.ks.base.utils.ConfigurationUtils;
import com.promiseland.ks.base.utils.FieldHelper;

public final class ViewHelper {
    public static void makeVisible(View... views) {
        changeVisibility(View.VISIBLE, views);
    }

    public static void makeVisibleWithAnimation(int duration, View... views) {
        if (views != null && views.length != 0) {
            for (View v : views) {
                v.setAlpha(0.0f);
                v.animate().alpha(1.0f).setDuration((long) duration).setListener(null);
                v.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void makeGone(View... views) {
        changeVisibility(View.GONE, views);
    }

    public static void makeInvisible(View... views) {
        changeVisibility(View.INVISIBLE, views);
    }

    public static void makeGoneWithAnimation(int duration, View... views) {
        if (views != null && views.length != 0) {
            for (final View view : views) {
                makeGoneWithAnimation(duration, new AnimatorListener() {
                    public void onAnimationStart(Animator animation) {
                    }

                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }

                    public void onAnimationCancel(Animator animation) {
                    }

                    public void onAnimationRepeat(Animator animation) {
                    }
                }, view);
            }
        }
    }

    public static void makeGoneWithAnimation(int duration, AnimatorListener listener, View view) {
        if (view != null) {
            view.setAlpha(1.0f);
            view.animate().alpha(0.0f).setDuration((long) duration).setListener(listener);
        }
    }

    private static void changeVisibility(int visibility, View... views) {
        if (views != null && views.length != 0) {
            for (View v : views) {
                if (v != null) {
                    v.setVisibility(visibility);
                }
            }
        }
    }

    public static void setIfNotEmpty(TextView view, String text) {
        if (!FieldHelper.INSTANCE.isEmpty((CharSequence) text)) {
            view.setText(text);
        }
    }

    public static void setTextViewTextOrHide(TextView view, String text) {
        if (FieldHelper.INSTANCE.isEmpty((CharSequence) text)) {
            makeGone(view);
            return;
        }
        makeVisible(view);
        view.setText(text);
    }

    public static int getPixelByDensity(Resources res, int dp) {
        return (int) (((double) (res.getDisplayMetrics().density * ((float) dp))) + 0.5d);
    }

    public static float getPixelByDensity(Resources res, double dp) {
        return (float) ((((double) res.getDisplayMetrics().density) * dp) + 0.5d);
    }

    public static int getDensityByPixels(int px) {
        return (int) (((float) px) / Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean isShownOnScreen(View view, int minPercentageViewed) {
        Rect mClipRect = new Rect();
        if (view == null || view.getVisibility() != View.VISIBLE || view.getParent() == null || !view.getGlobalVisibleRect(mClipRect)) {
            return false;
        }
        long visibleArea = ((long) mClipRect.height()) * ((long) mClipRect.width());
        long totalViewArea = ((long) view.getHeight()) * ((long) view.getWidth());
        if (totalViewArea <= 0 || 100 * visibleArea < ((long) minPercentageViewed) * totalViewArea) {
            return false;
        }
        return true;
    }

    public static boolean isShownBelowActionBar(View view, int offset) {
        if (view == null || view.getVisibility() != View.VISIBLE || view.getParent() == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        if (location[1] - offset <= ConfigurationUtils.getAppBarOffset(view.getContext())) {
            return false;
        }
        return true;
    }

    public static void startActivityWithSharedElementTransition(Activity activity, Intent intent, String sharedTransitionName, View sharedView) {
        if (sharedView == null || !APILevelHelper.isAPILevelMinimal(22)) {
            activity.startActivity(intent);
        } else {
            activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedView, sharedTransitionName).toBundle());
        }
    }

    @SafeVarargs
    public static void startActivityWithSharedElementTransition(Activity activity, Intent intent, Pair<View, String>... sharedViews) {
        if (sharedViews == null || !APILevelHelper.isAPILevelMinimal(22)) {
            activity.startActivity(intent);
        } else {
            activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedViews).toBundle());
        }
    }

    public static boolean isVisible(View view) {
        return view != null && view.getVisibility() == View.VISIBLE;
    }

    public static ObjectAnimator popup(View view, long duration) {
        view.setAlpha(0.0f);
        view.setVisibility(View.VISIBLE);
        ObjectAnimator popup = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofFloat("scaleX", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{0.0f, 1.0f})});
        popup.setDuration(duration);
        popup.setInterpolator(new OvershootInterpolator());
        return popup;
    }

    public static ObjectAnimator popout(final View view, long duration, final AnimatorListenerAdapter animatorListenerAdapter) {
        ObjectAnimator popout = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f}), PropertyValuesHolder.ofFloat("scaleX", new float[]{1.0f, 0.0f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{1.0f, 0.0f})});
        popout.setDuration(duration);
        popout.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
                if (animatorListenerAdapter != null) {
                    animatorListenerAdapter.onAnimationEnd(animation);
                }
            }
        });
        popout.setInterpolator(new AnticipateOvershootInterpolator());
        return popout;
    }

    public static void showKeyboardForEditText(final Context context,final EditText editText, boolean delayed) {
        editText.requestFocus();
        if (delayed) {
            editText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(editText, 1);
                }
            }, 100);
        } else {
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(editText, 1);
        }
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
