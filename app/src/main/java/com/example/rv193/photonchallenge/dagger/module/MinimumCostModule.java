package com.example.rv193.photonchallenge.dagger.module;

import com.example.rv193.photonlowestpath.ui.minimumcost.MinimumCostFragment;
import com.example.rv193.photonlowestpath.ui.minimumcost.MinimumCostInterface;
import com.example.rv193.photonlowestpath.ui.minimumcost.MinimumCostPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module
public abstract class MinimumCostModule {

    /**
     * Syntactic sugar - This is really a subcomponent
     * @return
     */
    @ContributesAndroidInjector(modules = AndroidSupportInjectionModule.class)
    abstract MinimumCostFragment minimumCostFragment();

    /**
     *  Provides the implementation of the MinimumCostInterface.Presenter
     * @param presenter
     * @return
     */
    @Binds
    public abstract MinimumCostInterface.Presenter providePresenter(MinimumCostPresenter presenter);
}