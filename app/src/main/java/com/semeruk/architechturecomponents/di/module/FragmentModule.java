package com.semeruk.architechturecomponents.di.module;

import com.semeruk.architechturecomponents.ui.fragment.UserProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract UserProfileFragment contributeUserProfileFragment();
}