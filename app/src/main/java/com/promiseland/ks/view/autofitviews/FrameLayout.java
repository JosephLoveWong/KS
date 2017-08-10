package com.promiseland.ks.view.autofitviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.promiseland.ks.dev.common.AutoFitable;
import com.promiseland.ks.dev.common.m;

public class FrameLayout extends android.widget.FrameLayout implements AutoFitable {
    private boolean a = true;
    private int b = 0;
    private int c = 0;

    public FrameLayout(Context context) {
        super(context);
    }

    public FrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public FrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.a = m.a(context, attributeSet);
    }

    public boolean getEnabledAutoFit() {
        return this.a;
    }

    public void setEnabledAutoFit(boolean z) {
        this.a = z;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        if (this.b == 0) {
            super.setPadding(m.b((View) this, i), m.c((View) this, i2), m.b((View) this, i3), m.c((View) this, i4));
            this.b++;
            return;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        if (this.c == 0) {
            super.setLayoutParams(m.a((View) this, layoutParams));
            this.c++;
            return;
        }
        super.setLayoutParams(layoutParams);
    }

    public void setAutoLayoutParams(LayoutParams layoutParams) {
        this.c = 1;
        super.setLayoutParams(m.a((View) this, layoutParams));
    }

    public void setMinimumHeight(int i) {
        super.setMinimumHeight(m.c((View) this, i));
    }

    public void setMinimumWidth(int i) {
        super.setMinimumWidth(m.b((View) this, i));
    }
}
