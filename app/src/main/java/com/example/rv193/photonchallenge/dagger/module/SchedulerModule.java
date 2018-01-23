package com.example.rv193.photonchallenge.dagger.module;

import com.example.rv193.photonlowestpath.dagger.BaseSchedulerProvider;
import com.example.rv193.photonlowestpath.dagger.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

    /**
     *  Provides the scheduler
     * @return
     */
    @Provides
    @Singleton
    BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }
}