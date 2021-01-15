package com.example.decorum.di.module;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RoomModule.class})
public class RepositoryModule {

    @Singleton
    @Provides
    ExecutorService providesExecutorService() {
        return Executors.newSingleThreadExecutor();
    }


    /*@Provides
    UserRepository provideAppAuthRepository(RetrofitService retrofitService) {
        return new UserRepository(retrofitService);
    }*/


}
