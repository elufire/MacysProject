package com.example.macysproject.di.modules

import com.example.macysproject.api.MainRepository
import com.example.macysproject.api.RemoteService
import com.example.macysproject.api.RetrofitHelper
import com.example.macysproject.viewmodels.HomePageViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//api key is 48e2fedf63175a2d6e271fcfbb13ca65

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRemoteService(retrofit: Retrofit) = retrofit.create(RemoteService::class.java)

    @Provides
    @Singleton
    fun provideRetrofitHelper(remoteService: RemoteService) = RetrofitHelper(remoteService)

    @Provides
    @Singleton
    fun provideMainRepository(retrofitHelper: RetrofitHelper) = MainRepository(retrofitHelper)


}