package com.promiseland.ks;

public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue> void onSuccess(final V response,
                                                          final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(final Throwable t,
                                                   final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onBefore(
            final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onProgress(final float progress, final long total, final int id,
                                                      final UseCase.UseCaseCallback<V> useCaseCallback);
}
