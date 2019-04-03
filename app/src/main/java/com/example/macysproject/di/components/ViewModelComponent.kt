package com.example.macysproject.di.components

import com.example.macysproject.di.modules.AppModule
import com.example.macysproject.di.modules.HomePageViewModelModule
import com.example.macysproject.di.modules.RetrofitModule
import com.example.macysproject.viewmodels.HomePageViewModel
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton


@Singleton
@Subcomponent(modules = [RetrofitModule::class])
interface ViewModelComponent {

    fun inject(target: HomePageViewModel)
}