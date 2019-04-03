package com.example.macysproject.di.components

import com.example.macysproject.activities.DisplayMovieActivity
import com.example.macysproject.activities.HomePageActivity
import com.example.macysproject.di.modules.AppModule
import com.example.macysproject.di.modules.HomePageViewModelModule
import com.example.macysproject.di.modules.RetrofitModule
import com.example.macysproject.viewmodels.HomePageViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, HomePageViewModelModule::class, RetrofitModule::class])
interface AppComponent {
    fun inject(target: HomePageActivity)

    fun inject(target: DisplayMovieActivity)

    fun inject(target: HomePageViewModel)
}