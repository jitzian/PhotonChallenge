package com.example.rv193.photonchallenge.dagger;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ImmediateSchedulerProvider implements BaseSchedulerProvider {

    @NonNull
    @Override
    public Scheduler getIoThread() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler getMainThread() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler getComputationThread() {
        return Schedulers.trampoline();
    }
}