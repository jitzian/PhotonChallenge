package com.example.rv193.photonchallenge.dagger.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    SharedPreferences provideDefaultPrerences(@NonNull Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}