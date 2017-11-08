package com.promiseland.ks.pages.introscreen;

import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.promiseland.ks.R;
import com.promiseland.ks.pages.BaseActivity_ViewBinding;

import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class IntroScreenActivity_ViewBinding extends BaseActivity_ViewBinding {
    private IntroScreenActivity target;
    private View view2131296355;
    private View view2131296359;
    private View view2131296361;
    private View view2131296366;
    private View view2131296367;

    public IntroScreenActivity_ViewBinding(final IntroScreenActivity target, View source) {
        super(target, source);
        this.target = target;
        target.mVideoBackground = (TextureView) Utils.findRequiredViewAsType(source, R.id.bg_video, "field 'mVideoBackground'", TextureView.class);
        View view = Utils.findRequiredView(source, R.id.btn_i_am_new, "field 'mIamNewButton' and method 'onIAmNewClick'");
        target.mIamNewButton = (Button) Utils.castView(view, R.id.btn_i_am_new, "field 'mIamNewButton'", Button.class);
        this.view2131296359 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onIAmNewClick();
            }
        });
        view = Utils.findRequiredView(source, R.id.btn_been_here, "field 'mBeenHereButton' and method 'onBeenHereClick'");
        target.mBeenHereButton = (Button) Utils.castView(view, R.id.btn_been_here, "field 'mBeenHereButton'", Button.class);
        this.view2131296355 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onBeenHereClick();
            }
        });
        target.mWelcome = (TextView) Utils.findRequiredViewAsType(source, R.id.welcome_text, "field 'mWelcome'", TextView.class);
        view = Utils.findRequiredView(source, R.id.btn_skip, "field 'mButtonSkip' and method 'onSkipClick'");
        target.mButtonSkip = (TextView) Utils.castView(view, R.id.btn_skip, "field 'mButtonSkip'", TextView.class);
        this.view2131296367 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSkipClick();
            }
        });
    }

    public void unbind() {
        IntroScreenActivity target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target.mVideoBackground = null;
        target.mIamNewButton = null;
        target.mBeenHereButton = null;
        target.mWelcome = null;
        target.mButtonSkip = null;
        this.view2131296359.setOnClickListener(null);
        this.view2131296359 = null;
        this.view2131296355.setOnClickListener(null);
        this.view2131296355 = null;
        this.view2131296367.setOnClickListener(null);
        this.view2131296367 = null;
        this.view2131296366.setOnClickListener(null);
        this.view2131296366 = null;
        this.view2131296361.setOnClickListener(null);
        this.view2131296361 = null;
        super.unbind();
    }
}
