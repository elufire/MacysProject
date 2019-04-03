package com.example.macysproject.di.components

import com.example.macysproject.di.modules.AppModule
import com.example.macysproject.di.modules.HomePageViewModelModule
import com.example.macysproject.di.modules.RetrofitModule
import com.example.macysproject.viewmodels.HomePageViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RetrofitModule::class])
interface ViewModelComponent {

    fun inject(target: HomePageViewModel)
}