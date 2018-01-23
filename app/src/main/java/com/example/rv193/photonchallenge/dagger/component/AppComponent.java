package com.example.rv193.photonchallenge.dagger.component;

import android.app.Application;

import com.example.rv193.photonchallenge.LowestPathApplication;
import com.example.rv193.photonchallenge.dagger.module.AppModule;
import com.example.rv193.photonchallenge.dagger.module.MainActivityModule;
import com.example.rv193.photonchallenge.dagger.module.MinimumCostModule;
import com.example.rv193.photonchallenge.dagger.module.SchedulerModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        MainActivityModule.class,
        MinimumCostModule.class,
        SchedulerModule.class})
public interface AppComponent {

    void inject(LowestPathApplication lowestPathApplication);

    @Component.Builder
    interface Builder {

        AppComponent build();

        @BindsInstance
        Builder application(Application application);
    }
}