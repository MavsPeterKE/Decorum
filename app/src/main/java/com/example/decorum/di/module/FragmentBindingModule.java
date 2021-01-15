package com.example.decorum.di.module;


import com.example.decorum.views.fragments.FavouritesFragment;
import com.example.decorum.views.fragments.FragmentDesignCategories;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract FavouritesFragment bindGameCountFragment();

    @ContributesAndroidInjector
    abstract FragmentDesignCategories bindFragmentScreens();
}
