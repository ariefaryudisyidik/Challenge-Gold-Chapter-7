package com.binar.ariefaryudisyidik.challengegoldchapter7

import android.app.Application
import com.binar.ariefaryudisyidik.challengegoldchapter7.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    storageModule
                )
            )
        }
    }
}