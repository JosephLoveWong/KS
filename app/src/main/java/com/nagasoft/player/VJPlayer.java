package com.nagasoft.player;

import android.util.Log;

import java.lang.ref.WeakReference;

public class VJPlayer {
    private static boolean gbload;
    private int mNativeListener = 0;
    private int mNativePlayer = 0;
    private String mStrVideoURL;
    private UrlChanged mUCCallback = null;
    private OnVJMSErrorListener mVELCallback = null;

    static {
        gbload = false;
        try {

            System.loadLibrary("p2pcore");
            System.loadLibrary("vjplayer_jni");

//            System.loadLibrary("dbapi");
//            System.loadLibrary("ijkffmpeg");
//            System.loadLibrary("ijkplayer");
//            System.loadLibrary("ijksdl");
//            System.loadLibrary("supertv");
            gbload = true;
            native_init();
        } catch (Throwable th) {
            Log.e("MMM", "error " + th.getMessage());
            gbload = false;
        }
    }

    public VJPlayer(UrlChanged urlChanged) {
        if (gbload) {
            native_setup(new WeakReference(this));
        }
        this.mUCCallback = urlChanged;
    }

    public static final native void native_init();

    public native void _release();

    public native long getPlayBackDuration();

    public String getVideoURL() {
        return this.mStrVideoURL;
    }

    public native boolean isLiveStream();

    public native boolean isPlayBackStream();

    public native boolean isVodFile();

    public final native void native_finalize();

    public final native void native_setup(Object obj);

    public void notifyError(int i) {
        notifyUIError(i);
    }

    public void notifyPlayURL(String str) {
        this.mStrVideoURL = str;
        notifyUI(str);
    }

    protected void notifyUI(String str) {
        if (this.mUCCallback != null) {
            this.mUCCallback.onUrlChanged(str);
        }
    }

    protected void notifyUIError(int i) {
        if (this.mVELCallback != null) {
            this.mVELCallback.onVJMSError(i);
        }
    }

    public native boolean seekPlayBack(long j);

    public void setOnVJMSErrorListener(OnVJMSErrorListener onVJMSErrorListener) {
        this.mVELCallback = onVJMSErrorListener;
    }

    public native void setURL(String str);

    public native void setVJMSBufferTimeout(int i);

    public native boolean start();

    public native void stop();
}
