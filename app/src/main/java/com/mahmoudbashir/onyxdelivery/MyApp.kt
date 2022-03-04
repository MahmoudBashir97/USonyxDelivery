package com.mahmoudbashir.onyxdelivery

import android.app.Application
import com.mahmoudbashir.onyxdelivery.koin.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            module {
                dataModule
            }
        }
    }
}