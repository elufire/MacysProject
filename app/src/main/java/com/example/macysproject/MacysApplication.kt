package com.example.macysproject

import android.app.Application
import com.example.macysproject.di.components.AppComponent
import com.example.macysproject.di.components.DaggerAppComponent
import com.example.macysproject.di.components.DaggerViewModelComponent
import com.example.macysproject.di.components.ViewModelComponent
import com.example.macysproject.di.modules.AppModule

class MacysApplication : Application() {

    val macysComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    val viewModelComponent: ViewModelComponent by lazy {
        DaggerViewModelComponent.builder()
            .build()
    }
}