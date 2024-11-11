package com.example.simpletodo

import android.app.Application
import com.example.simpletodo.features.core.di.coreFeatModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SimpleTodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SimpleTodoApp)
            modules(coreFeatModule)
        }
    }
}