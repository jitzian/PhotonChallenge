package com.example.rv193.photonchallenge.ui.base;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public interface BaseContract {

    interface View {
        void showMessage(@StringRes int message);
        void setLoading(boolean isLoading);
        void finishView();
    }

    interface Presenter<T extends View> {
        @SuppressWarnings("unchecked")
        void onViewAttached(@NonNull T view);
        void onViewDetached();
    }
}