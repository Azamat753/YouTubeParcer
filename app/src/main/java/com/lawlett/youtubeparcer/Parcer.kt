package com.lawlett.youtubeparcer

import android.app.Application
import com.lawlett.youtubeparcer.di.networkModule
import com.lawlett.youtubeparcer.di.repositoryModule
import com.lawlett.youtubeparcer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Parcer : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Parcer)
            modules(listOf(viewModelModule, repositoryModule, networkModule))
        }
    }
}