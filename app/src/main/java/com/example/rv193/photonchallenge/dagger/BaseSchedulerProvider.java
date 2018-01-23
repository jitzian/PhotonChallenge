package com.example.rv193.photonchallenge.dagger;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler getIoThread();

    @NonNull
    Scheduler getMainThread();

    @NonNull
    Scheduler getComputationThread();
}