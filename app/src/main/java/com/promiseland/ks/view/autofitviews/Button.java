package com.promiseland.ks.view.autofitviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.promiseland.ks.dev.common.AutoFitable;
import com.promiseland.ks.dev.common.m;

import net.sf.chineseutils.ChineseUtils;

public class Button extends android.support.v7.widget.AppCompatButton implements AutoFitable {
    private boolean a = true;
    private int b = 0;
    private int c = 0;

    public Button(Context context) {
        super(context);
    }

    public Button(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public Button(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        setTextSize(getTextSize());
        this.a = m.a(context, attributeSet);
    }

    public boolean getEnabledAutoFit() {
        return this.a;
    }

    public void setEnabledAutoFit(boolean z) {
        this.a = z;
    }

    public void setTextSize(float f) {
        setTextSize(0, (float) m.a((View) this, (int) f));
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(0, f);
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        if (this.b == 0) {
            super.setPadding(m.b((View) this, i), m.c((View) this, i2), m.b((View) this, i3), m.c((View) this, i4));
            this.b++;
            return;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setCompoundDrawablePadding(int i) {
        super.setCompoundDrawablePadding(m.a((View) this, i));
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        if (this.c == 0) {
            super.setLayoutParams(m.a((View) this, layoutParams));
            this.c++;
            return;
        }
        super.setLayoutParams(layoutParams);
    }

    public void setMinimumHeight(int i) {
        super.setMinimumHeight(m.c((View) this, i));
    }

    public void setMinimumWidth(int i) {
        super.setMinimumWidth(m.a((View) this, i));
    }

    public void setTypeface(Typeface typeface) {
        Typeface h = m.h(getContext().getApplicationContext());
        if (m.a || h == null) {
            super.setTypeface(typeface);
        } else {
            super.setTypeface(h);
        }
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        if (!m.a || charSequence == null || charSequence.length() <= 0) {
            super.setText(charSequence, bufferType);
        } else {
            super.setText(ChineseUtils.simpToTrad(charSequence.toString()), bufferType);
        }
    }
}
