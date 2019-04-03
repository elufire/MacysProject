package com.example.macysproject.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.macysproject.viewmodels.HomePageViewModel
import com.example.macysproject.viewmodels.UniversalViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomePageViewModelModule {
    @Provides
    @Singleton
    fun provideHomePageViewModel(): ViewModelProvider.Factory = UniversalViewModelFactory{HomePageViewModel()}
}