package com.semeruk.architechturecomponents.di.component;

import android.app.Application;

import com.semeruk.architechturecomponents.CustomApplication;
import com.semeruk.architechturecomponents.di.module.ActivityModule;
import com.semeruk.architechturecomponents.di.module.ApplicationModule;
import com.semeruk.architechturecomponents.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules={AndroidSupportInjectionModule.class, ActivityModule.class, FragmentModule.class, ApplicationModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }

    void inject(CustomApplication application);
}