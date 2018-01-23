package com.example.rv193.photonchallenge.ui.base;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {

    @NonNull
    protected final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    @Nullable
    private T mView;

    public T getView() {
        return mView;
    }

    @Override
    public void onViewAttached(@NonNull T view) {
        mView = view;
    }

    @Override
    public void onViewDetached() {
        mCompositeDisposable.clear();
        mView = null;
    }
}