package com.example.workmanagerdemo

import android.app.Application
import timber.log.Timber

@Suppress("unused")
class WorkManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}