package io.github.katatoshi.android.dialogfragmentwithviewmodel

import android.app.Application
import timber.log.Timber

class DialogFragmentwithViewModelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}