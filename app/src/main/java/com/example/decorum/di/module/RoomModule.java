package com.example.decorum.di.module;

import android.app.Application;

import androidx.room.Room;

import com.example.decorum.database.AppDataBase;
import com.example.decorum.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Singleton
    @Provides
    AppDataBase provideAppDataBase(Application application) {
        return Room.databaseBuilder(application, AppDataBase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

  /*  @Singleton
    @Provides
    ContainerDao provideContainerDao(AppDataBase appDataBase) {
        return appDataBase.containerDao();
    }*/


}
