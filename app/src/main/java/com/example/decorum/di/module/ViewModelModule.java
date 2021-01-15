package com.example.decorum.di.module;

import androidx.lifecycle.ViewModel;

import com.example.decorum.di.util.ViewModelKey;
import com.example.decorum.viewmodel.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel gameCountViewModel);
}
