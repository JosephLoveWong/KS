package com.promiseland.ks.dev.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;

import java.math.BigDecimal;

public final class m {
    public static boolean a = false;
    private static float b;
    private static int c;
    private static int d;
    private static int e;
    private static float f = 1280.0f;
    private static float g = 720.0f;
    private static float h = 0.0f;
    private static float i = 0.0f;
    private static float j;
    private static boolean k = false;
    private static Typeface l;

    public static int a(Context context) {
        if (d == 0) {
            synchronized (m.class) {
                if (d == 0) {
                    int a = a(context, true);
                    return a;
                }
            }
        }
        return d;
    }

    public static int b(Context context) {
        if (c == 0) {
            synchronized (m.class) {
                if (c == 0) {
                    int a = a(context, false);
                    return a;
                }
            }
        }
        return c;
    }

    public static float c(Context context) {
        if (j == 0.0f) {
            synchronized (m.class) {
                if (j == 0.0f) {
                    j = Math.min(d(context), e(context));
                }
            }
        }
        return j;
    }

    public static float d(Context context) {
        if (h == 0.0f) {
            synchronized (m.class) {
                if (h == 0.0f) {
                    if (k) {
                        h = ((float) Math.min(b(context), a(context))) / g;
                    } else {
                        h = ((float) Math.max(b(context), a(context))) / f;
                    }
                }
            }
        }
        return h;
    }

    public static float e(Context context) {
        if (i == 0.0f) {
            synchronized (m.class) {
                if (i == 0.0f) {
                    if (k) {
                        i = ((float) Math.max(b(context), a(context))) / f;
                    } else {
                        i = ((float) Math.min(b(context), a(context))) / g;
                    }
                }
            }
        }
        return i;
    }

    public static float f(Context context) {
        if (b == 0.0f) {
            synchronized (m.class) {
                if (b == 0.0f) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    ((WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
                    b = displayMetrics.density;
                }
            }
        }
        return b;
    }

    public static int g(Context context) {
        if (e == 0) {
            synchronized (m.class) {
                if (e == 0) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    ((WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
                    e = displayMetrics.densityDpi;
                }
            }
        }
        return e;
    }

    public static int a(View view, int i) {
        boolean z = true;
        if (view instanceof AutoFitable) {
            z = ((AutoFitable) view).getEnabledAutoFit();
        }
        return a(view.getContext(), i, z);
    }

    public static float a(View view, float f) {
        boolean z = true;
        if (view instanceof AutoFitable) {
            z = ((AutoFitable) view).getEnabledAutoFit();
        }
        return a(view.getContext(), f, z);
    }

    public static int a(Context context, int i) {
        return a(context, i, true);
    }

    public static int a(Context context, int i, boolean z) {
        if (!z) {
            return i;
        }
        float c = c(context);
        if (((double) Math.abs(d(context) - e(context))) < 0.15d) {
            c = d(context);
        }
        return new BigDecimal((double) (c * ((float) i))).setScale(0, 4).intValue();
    }

    public static int b(Context context, int i) {
        return a(context, i, true);
    }

    public static int b(View view, int i) {
        return a(view, i);
    }

    public static int b(Context context, int i, boolean z) {
        return a(context, i, z);
    }

    public static float a(Context context, float f, boolean z) {
        return !z ? f : (float) new BigDecimal((double) (d(context) * f)).setScale(0, 4).intValue();
    }

    public static int c(Context context, int i) {
        return c(context, i, true);
    }

    public static int c(View view, int i) {
        boolean z = true;
        if (view instanceof AutoFitable) {
            z = ((AutoFitable) view).getEnabledAutoFit();
        }
        return c(view.getContext(), i, z);
    }

    public static int c(Context context, int i, boolean z) {
        if (!z) {
            return i;
        }
        float c = c(context);
        if (((double) Math.abs(d(context) - e(context))) < 0.15d) {
            c = e(context);
        }
        return new BigDecimal((double) (c * ((float) i))).setScale(0, 4).intValue();
    }

    public static LayoutParams a(View view, LayoutParams layoutParams) {
        boolean enabledAutoFit;
        Object obj = null;
        if (view instanceof AutoFitable) {
            enabledAutoFit = ((AutoFitable) view).getEnabledAutoFit();
        } else {
            enabledAutoFit = true;
        }
        if (enabledAutoFit) {
            if (layoutParams instanceof MarginLayoutParams) {
                ((MarginLayoutParams) layoutParams).leftMargin = b(view, ((MarginLayoutParams) layoutParams).leftMargin);
                ((MarginLayoutParams) layoutParams).rightMargin = b(view, ((MarginLayoutParams) layoutParams).rightMargin);
                ((MarginLayoutParams) layoutParams).topMargin = c(view, ((MarginLayoutParams) layoutParams).topMargin);
                ((MarginLayoutParams) layoutParams).bottomMargin = c(view, ((MarginLayoutParams) layoutParams).bottomMargin);
            }
            if (!(layoutParams.width == -2 || layoutParams.width == -1)) {
                Object obj2 = layoutParams.width == layoutParams.height ? 1 : null;
                layoutParams.width = b(view, layoutParams.width);
                obj = obj2;
            }
            if (!(layoutParams.height == -2 || layoutParams.height == -1)) {
                if (obj != null) {
                    layoutParams.height = b(view, layoutParams.height);
                } else {
                    layoutParams.height = c(view, layoutParams.height);
                }
            }
        }
        return layoutParams;
    }

    public static boolean a(Context context, AttributeSet attributeSet) {
//        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.VST_AUTO_FIT);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, null);
        boolean z = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        return z;
    }

    public static boolean b(Context context, AttributeSet attributeSet) {
//        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.VST_IMG_ANIMATION);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, null);
        boolean z = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        return z;
    }

    public static int c(Context context, AttributeSet attributeSet) {
//        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.VST_IMG_BACKGROUND);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, null);
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static Typeface h(Context context) {
        if (l == null) {
            synchronized (m.class) {
                if (l == null) {
                    try {
                        l = Typeface.createFromAsset(context.getAssets(), "fonts/flfbls.ttf");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return l;
    }

    public static int a(Context context, boolean z) {
        if (c * d == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            c = displayMetrics.widthPixels;
            d = displayMetrics.heightPixels;
        }
        if (z) {
            return d;
        }
        return c;
    }
}
