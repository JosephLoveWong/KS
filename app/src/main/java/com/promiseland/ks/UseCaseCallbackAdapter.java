package com.promiseland.ks;

/**
 * Created by Administrator on 2017/6/12.
 */

public class UseCaseCallbackAdapter<R> implements UseCase.UseCaseCallback<R> {
    @Override
    public void onSuccess(R response) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onBefore() {

    }

    @Override
    public void onProgress(float progress, long total, int id) {

    }
}
