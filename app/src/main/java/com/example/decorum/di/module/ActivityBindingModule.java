package com.example.decorum.di.module;

import com.example.decorum.views.activities.HomeActivity;
import com.example.decorum.views.activities.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract HomeActivity bindHomeActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();
}
