package com.moladin

import android.app.Application
import com.moladin.com.moladin.koin.repositoryModule
import com.moladin.com.moladin.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoladinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoladinApplication)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }

}