package com.semeruk.architechturecomponents.di.model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.semeruk.architechturecomponents.di.ViewModelKey;
import com.semeruk.architechturecomponents.viewmodel.FactoryViewModel;
import com.semeruk.architechturecomponents.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factoryViewModel);
}
