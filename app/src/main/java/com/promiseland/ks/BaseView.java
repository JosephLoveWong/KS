package com.promiseland.ks;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
