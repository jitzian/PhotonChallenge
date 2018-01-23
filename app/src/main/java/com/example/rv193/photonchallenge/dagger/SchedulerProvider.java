package com.example.rv193.photonchallenge.dagger;

import android.support.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider implements BaseSchedulerProvider {

    @NonNull
    @Override
    public Scheduler getIoThread() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler getMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    @Override
    public Scheduler getComputationThread() {
        return Schedulers.computation();
    }
}