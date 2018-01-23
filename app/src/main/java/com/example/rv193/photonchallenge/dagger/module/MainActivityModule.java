package com.example.rv193.photonchallenge.dagger.module;

import com.example.rv193.photonchallenge.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}