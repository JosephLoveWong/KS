package com.promiseland.ks;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseCaseThreadPoolUiScheduler implements UseCaseScheduler {

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    ExecutorService mThreadPoolExecutor;

    public UseCaseThreadPoolUiScheduler() {
        mThreadPoolExecutor = Executors.newCachedThreadPool();
    }

    @Override
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    @Override
    public <V extends UseCase.ResponseValue> void onSuccess(final V response,
            final UseCase.UseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onSuccess(response);
            }
        });
    }

    @Override
    public <V extends UseCase.ResponseValue> void onError(final Throwable t,
            final UseCase.UseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onError(t);
            }
        });
    }

    @Override
    public <V extends UseCase.ResponseValue> void onBefore(final UseCase.UseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onBefore();
            }
        });
    }

    @Override
    public <V extends UseCase.ResponseValue> void onProgress(final float progress, final long total, final int id, final UseCase.UseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onProgress(progress, total, id);
            }
        });
    }

}
